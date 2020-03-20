package com.lyf.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyf.common.LyfTools;
import com.lyf.common.Page;
import com.lyf.common.SupperDao;
import com.lyf.po.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
https://api.apiopen.top/getWangYiNews
 */
@Controller
public class NewsCon {


    @Autowired
    SupperDao dao;

    @RequestMapping("news_list")
    @ResponseBody
    private JSONObject news_list(String keywords, @RequestBody Page page) {
        List<News> list = null;
        if (LyfTools.isNotEmpty(keywords)) {
            list = dao.findByPage("from News where title like concat('%',?1,'%') order by convertGBK(passtime)", page, new Object[]{keywords});
        } else {
            list = dao.findByPage("from News", page, null);
        }
        JSONObject jo = new JSONObject();
        JSONArray data = new JSONArray();
        list.forEach(po -> data.add(po));
        jo.put("data", data);
        jo.put("code", 0);
        jo.put("msg", "");
        jo.put("count", dao.findOneBySql("select count(*) from news", null));
        return jo;
    }

    @RequestMapping("news_toSaveUpdate")
    public String news_toSaveUpdate(Integer id, ModelMap m) {
        if (LyfTools.isNotEmpty(id)) {
            News po = (News) dao.get(News.class, id);
            m.put("po", po);
        }
        return "news/saveUpdate.jsp";
    }

    @RequestMapping("news_saveUpdate")
    @ResponseBody
    public JSONObject news_saveUpdate(@RequestBody News po) {
        JSONObject jo = new JSONObject();
        if (LyfTools.isEmpty(po.getId())) {
            dao.save(po);
        } else {
            dao.update(po);
        }
        jo.put("ok", true);
        return jo;
    }

    @RequestMapping("news_del")
    @ResponseBody
    public JSONObject news_del(Integer[] ids) {
        dao.delAll(News.class, ids);
        JSONObject jo = new JSONObject();
        jo.put("ok", true);
        return jo;
    }

}
