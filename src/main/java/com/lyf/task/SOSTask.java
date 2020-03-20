//package com.lyf.task;
//
//import com.lyf.common.LyfTools;
//import com.lyf.common.SupperDao;
//import com.lyf.controller.WebSocketServer1;
//import com.lyf.po.Warning;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Controller;
//
//import javax.websocket.Session;
//import java.util.List;
//
//@Controller
//@EnableScheduling
//public class SOSTask implements SchedulingConfigurer {
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
//    private String cron = "0/5 * * * * ?";
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(() -> {
//                    try {
//                        String msg = SOS();
//                        if (LyfTools.isNotEmpty(msg)) {
//                            Session session = WebSocketServer1.session;
//                            if (LyfTools.isNotEmpty(session)) {
//                                //服务器向客户端主动推送SOS报警信息
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
//    //SOS报警信息
//    public String SOS() {
//        //查询60分钟之内的报警信息
//        String sql = "SELECT * FROM `Warning` b WHERE type = 1 AND createdDate >= DATE_SUB(NOW(), INTERVAL 60 MINUTE) AND isHandle IS NULL;";
//        List<Warning> warningList = dao.findBySql(Warning.class, sql, null);
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < warningList.size(); i++) {
//            sb.append("<span style=color:red;display:block;margin:5px 0>" + (i + 1) + ".&nbsp;&nbsp;&nbsp;&nbsp;" + warningList.get(i).getContent() + "----" + warningList.get(i).getCreatedDate() + "</span><br>");
//        }
//        return sb.toString();
//    }
//}
