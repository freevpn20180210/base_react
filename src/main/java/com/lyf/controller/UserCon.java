package com.lyf.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyf.common.LyfTools;
import com.lyf.common.Page;
import com.lyf.common.SupperDao;
import com.lyf.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserCon {


    @Autowired
    SupperDao dao;

    @RequestMapping("user_list")
    @ResponseBody
    private JSONObject user_list(String keywords, Page page) {
        List<User> list = null;
        if (LyfTools.isNotEmpty(keywords)) {
            list = dao.findByPage("from User where name like concat('%',?1,'%') order by convertGBK(name)", page, new Object[]{keywords});
        } else {
            list = dao.findByPage("from User order by convertGBK(name)", page, null);
        }
        JSONObject jo = new JSONObject();
        JSONArray data = new JSONArray();
        list.forEach(po -> data.add(po));
        jo.put("data", data);
        jo.put("code", 0);
        jo.put("msg", "");
        jo.put("count", dao.findOneBySql("select count(*) from user", null));
        return jo;
    }

    @RequestMapping("user_toSaveUpdate")
    public String user_toSaveUpdate(Integer id, ModelMap m) {
        if (LyfTools.isNotEmpty(id)) {
            User po = (User) dao.get(User.class, id);
            m.put("po", po);
        }
        return "user/saveUpdate.jsp";
    }

    @RequestMapping("user_saveUpdate")
    @ResponseBody
    public JSONObject user_saveUpdate(User po) {
        JSONObject jo = new JSONObject();
        if (LyfTools.isEmpty(po.getId())) {
            dao.save(po);
        } else {
            dao.update(po);
        }
        jo.put("ok", true);
        return jo;
    }

    @RequestMapping("user_del")
    @ResponseBody
    public JSONObject user_del(Integer[] ids) {
        dao.delAll(User.class, ids);
        JSONObject jo = new JSONObject();
        jo.put("ok", true);
        return jo;
    }

}
