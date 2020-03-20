/*
package com.lyf.tool;

import com.lyf.common.SupperDao;
import com.lyf.realm.LoginRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * 更新权限并清空权限缓存
 *//*

@Component
public class ClearAuth {

    @Autowired
    SupperDao dao;

    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;

    //更新完权限后清除权限缓存使之即时生效--后执行
    public static void clearAuthorizationInfo() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        LoginRealm loginRealm = (LoginRealm) rsm.getRealms().iterator().next();
        loginRealm.clearAuthorizationInfo();
    }

    //更新权限信息--先执行
    public synchronized void updatePermission() {
        try {
            AbstractShiroFilter abstractFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            //获取过滤器链管理器
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) abstractFilter.getFilterChainResolver();
            DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空过滤器链,即清空初始的拦截配置
            filterChainManager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

            Map<String, String> map = new LinkedHashMap<>();

            //这些url无论是否认证都可以访问
            map.put("/layuiadmin/**", "anon");
            map.put("/my/**", "anon");

            //登录
            map.put("/userLogin", "anon");
            map.put("/views/login.jsp", "anon");
            map.put("/getKaptcha", "anon");

            //datav
            map.put("/datav_getMarkForOnlineCurx_monitor", "anon");
            map.put("/datav_getMarkForOnlineCurx_special", "anon");
            map.put("/datav_getMarkForOnlineCurx_normal", "anon");
            map.put("/datav_getMarkForNotOnline", "anon");
            map.put("/datav_getMarkForOnlineCurx_test", "anon");
            map.put("/staffPic/**", "anon");
            map.put("/views/isOnline/trajectory.jsp", "anon");
            map.put("/getLocationsByImei", "anon");
            map.put("/datav_getProjectOnline", "anon");
            map.put("/datav_getAreaOnline", "anon");
            map.put("/online_waring", "anon");

            //忘记密码
            map.put("/views/sys/user/forget.jsp", "anon");
            map.put("/isMailExist", "anon");
            map.put("/sendCode", "anon");
            map.put("/findPwd", "anon");
            map.put("/resetPwd", "anon");

            //通过认证之后,角色也需要对应权限才能访问--添加就拦截,不添加就不拦截
            List<String> list = (List<String>) dao.find("select url from ZhPermission where url <> ''", null);
            list.forEach(url -> {
                map.put("/" + url, "perms[" + url + "]");
            });

            //其他url都必须认证后才可以访问,该行代码必须放在最后面
            map.put("/**", "user");

            //putAll可以合并两个map，只不过如果有相同的key那么用后面的覆盖前面的
            shiroFilterFactoryBean.getFilterChainDefinitionMap().putAll(map);
            map.forEach((k, v) -> {
                filterChainManager.createChain(k, v);
            });
            clearAuthorizationInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
