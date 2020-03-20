//package com.lyf.task;
//
//import com.alibaba.fastjson.JSONObject;
//import com.lyf.common.LyfTools;
//import com.lyf.common.SupperDao;
//import com.lyf.controller.WebSocketServer;
//import com.lyf.dto.ZhStaffLocation;
//import com.lyf.po.Warning;
//import com.lyf.po.ZhPolygon;
//import com.lyf.tool.DistanceUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.websocket.Session;
//import java.awt.geom.Point2D;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Controller
//@EnableScheduling
//public class WarningTask implements SchedulingConfigurer {
//
//    @Autowired
//    SupperDao dao;
//
//    //时间表达式格式:秒,分,时,日,月,周
//    //举例说明
//    //例1：每隔5秒执行一次：*/5 * * * * ?
//    //
//    //例2：每隔5分执行一次：0 */5 * * * ?
//    //         在26分、29分、33分执行一次：0 26,29,33 * * * ?
//    //
//    //例3：每天半夜12点30分执行一次：0 30 0 * * ? （注意日期域为0不是24）
//    //         每天凌晨1点执行一次：0 0 1 * * ?
//    //         每天上午10：15执行一次： 0 15 10 ? * * 或 0 15 10 * * ? 或 0 15 10 * * ? *
//    //         每天中午十二点执行一次：0 0 12 * * ?
//    //         每天14点到14：59分，每1分钟执行一次：0 * 14 * * ?
//    //         每天14点到14：05分，每1分钟执行一次：0 0-5 14 * * ?
//    //         每天14点到14：55分，每5分钟执行一次：0 0/5 14 * * ?
//    //         每天14点到14：55分，和18点到18点55分，每5分钟执行一次：0 0/5 14,18 * * ?
//    //         每天18点执行一次：0 0 18 * * ?
//    //         每天18点、22点执行一次：0 0 18,22 * * ?
//    //         每天7点到23点，每整点执行一次：0 0 7-23 * * ?
//    //         每个整点执行一次：0 0 0/1 * * ?
//    //
//    //例4：每月1号凌晨1点执行一次：0 0 1 1 * ?
//    //         每月15号的10点15分执行一次：0 15 10 15 * ?
//    //         每月的最后一天的10：15执行一次：0 15 10 L * ?
//    //
//    //例5：每月最后一天23点执行一次：0 0 23 L * ?
//    //
//    //例6：每周星期天凌晨1点执行一次：0 0 1 ? * L
//    //         三月的每周三的14：10和14：44执行一次：0 10,44 14 ? 3 WED
//    //         每个周一、周二、周三、周四、周五的10：15执行一次：0 15 10 ? * MON-FRI
//    //         每月最后一个周五的10：15执行一次：0 15 10 ? * 6L
//    //
//    //﻿
//    //例7：2016年的每天早上10：15执行一次: 0 15 10 * * ? 2016
//
//
//    //每隔5秒执行一次
////    private String cron = "0/5 * * * * ?";
//    //每隔10秒执行一次
////    private String cron = "0/10 * * * * ?";
//    //每隔15分钟执行一次
//    private String cron = "0 */15 * * * ?";
//
//    //此次任务离结束的剩余时间
//    private int remainingTime;
//
//    public int getRemainingTime() {
//        return remainingTime;
//    }
//
//    public void setRemainingTime() {
//        remainingTime = 0;
//        //获取表达式表示的时间间隔
//        int length = cron.length();
//        int time = length == 13 ? Integer.valueOf(cron.substring(4, 5)) * 60 : Integer.valueOf(cron.substring(4, 6)) * 60;
//        remainingTime = time;
//    }
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(() -> {
//                    try {
//                        //开始计时
//                        setRemainingTime();
//                        String msg = leave();
//                        if (LyfTools.isNotEmpty(msg)) {
//                            Session session = WebSocketServer.session;
//                            if (LyfTools.isNotEmpty(session)) {
//                                //服务器向客户端主动推送人员离开围栏的报警信息
//                                session.getBasicRemote().sendText(msg);
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }, triggerContext -> {
//                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
//                }
//        );
//    }
//
//    //人员离开围栏的报警信息
//    public String leave() throws ParseException {
//        //查询在线的人员和位置信息和工程名
//        String sql = "SELECT d.id, d. name, d.sex, d.pic, d.role, d.imei, d.project_id, c.lng, c.lat, c.createdDate, e. name projectName FROM zh_staff d JOIN ( SELECT b.imei imei1, b.longitude lng, b.latitude lat, b.createdDate FROM ( SELECT MAX(id) AS id FROM Location WHERE createdDate >= DATE_SUB(NOW(), INTERVAL 60 MINUTE) GROUP BY imei ) AS a LEFT JOIN Location b ON b.id = a.id ) c ON d.imei = c.imei1 LEFT JOIN zh_project e ON project_id = e.id";
//        List<ZhStaffLocation> zhStaffLocationList = dao.findBySqlNoMapping(ZhStaffLocation.class, sql, null);
//        //检测点
//        Point2D.Double p = null;
//        //多边形坐标集合
//        List<ZhPolygon> polygonList = null;
//        //多边形各顶点集合
//        List<Point2D.Double> pList = null;
//        //是否在围栏里
//        Boolean isIn = null;
//        //人员离开围栏的报警信息
//        StringBuilder sb = new StringBuilder();
//        String msg = null;
//        int i = 0;
//        for (ZhStaffLocation po : zhStaffLocationList) {
//            p = new Point2D.Double(Double.valueOf(po.getLng()), Double.valueOf(po.getLat()));
//            polygonList = dao.findBySql(ZhPolygon.class, "SELECT * FROM zh_polygon WHERE project_id =?1 ORDER BY id DESC", new Object[]{po.getProject_id()});
//            if (LyfTools.listNotEmpty(polygonList)) {
//                pList = new ArrayList<>();
//                //转换
//                for (ZhPolygon zhPolygon : polygonList) {
//                    pList.add(new Point2D.Double(Double.valueOf(zhPolygon.getX()), Double.valueOf(zhPolygon.getY())));
//                }
//                //对比,点->多边形
//                isIn = DistanceUtils.isInPolygon(p, pList);
//                if (isIn == false) {
//                    i++;
//                    msg = String.format("请注意,%s人员,%s,离开了[%s]的围栏", po.getRole(), po.getName(), po.getProjectName());
//                    Warning warning = new Warning();
//                    warning.setImei(po.getImei());
//                    warning.setType(2);
//                    warning.setContent(msg);
//                    warning.setCreatedDate(LyfTools.date2DateFormat(new Date()));
//                    dao.save(warning);
//                    sb.append("<span style=color:red;display:block;margin:5px 0>" + i + ".&nbsp;&nbsp;&nbsp;&nbsp;" + msg + "----" + LyfTools.formatDate(new Date()) + "</span><br>");
//                }
//            } else {
//                isIn = true;
//            }
//        }
//        return sb.toString();
//    }
//
//    //获得当前任务执行周期
//    @RequestMapping("getFenceTestTime")
//    @ResponseBody
//    private JSONObject getFenceTestTime() {
//        JSONObject jo = new JSONObject();
//        jo.put("cron", cron);
//        jo.put("remainingTime", remainingTime);
//        jo.put("ok", true);
//        return jo;
//    }
//
//    //设置当前任务执行周期
//    @RequestMapping("setFenceTestTime")
//    @ResponseBody
//    private JSONObject setFenceTestTime(String cron) {
//        this.cron = cron;
//        JSONObject jo = new JSONObject();
//        jo.put("ok", true);
//        return jo;
//    }
//
//    @Async
//    @Scheduled(fixedDelay = 1000)  //间隔1秒
//    public void calTime() {
//        if (remainingTime > 0) {
//            remainingTime--;
//        }
//    }
//
//    @Async
//    @Scheduled(fixedDelay = 1000 * 60 * 3)  //间隔180秒
//    public void delWidget() {
//        //不知道为什么本程序停止了还会有新记录插入
//        dao.executeSql("delete from Warning where type is null", null);
//    }
//
//}
