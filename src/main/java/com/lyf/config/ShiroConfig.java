/*
package com.lyf.config;

import com.lyf.common.SupperDao;
import com.lyf.realm.LoginRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootConfiguration
public class ShiroConfig {

    */
/**
 * 配置shiro的url拦截规则
 * shiro生命周期:
 * 1.该类在容器启动时执行,配置拦截规则(认证前规则,认证后规则)
 * 2.进入realm认证
 * 3.认证之后,当输入需要认证的url后,shiro会读取角色对应的权限url,若该url不存在,则拦截,否则放行
 * <p>
 * shiro的url过滤器
 * anno:不拦截,任何人都可以访问
 * authc:登录之后才能进行访问，不包括remember me
 * user:登录之后才能进行访问，包括remember me
 * <p>
 * shiro的url过滤器
 * anno:不拦截,任何人都可以访问
 * authc:登录之后才能进行访问，不包括remember me
 * user:登录之后才能进行访问，包括remember me
 * <p>
 * shiro的url过滤器
 * anno:不拦截,任何人都可以访问
 * authc:登录之后才能进行访问，不包括remember me
 * user:登录之后才能进行访问，包括remember me
 * <p>
 * shiro的url过滤器
 * anno:不拦截,任何人都可以访问
 * authc:登录之后才能进行访问，不包括remember me
 * user:登录之后才能进行访问，包括remember me
 *//*

 */
/**
 * shiro的url过滤器
 * anno:不拦截,任何人都可以访问
 * authc:登录之后才能进行访问，不包括remember me
 * user:登录之后才能进行访问，包括remember me
 *//*

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SupperDao dao) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl("/views/login.jsp");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/views/403.jsp");

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

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //    shiro的密码解密器
    @Bean
    public HashedCredentialsMatcher matcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        return matcher;
    }

    //    认证授权realm
    @Bean
    public LoginRealm loginRealm() {
        LoginRealm loginRealm = new LoginRealm();
        loginRealm.setCredentialsMatcher(matcher());
        return loginRealm;
    }

    //    shiro的缓存管理器
    @Bean
    public MemoryConstrainedCacheManager cacheManager() {
        MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
        return cacheManager;
    }

    //shiro创建的cookie对象
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(24 * 60 * 60 * 30);
        return simpleCookie;
    }

    //shiro的cookie管理器
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
//        rememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        rememberMeManager.setCookie(rememberMeCookie());
        return rememberMeManager;
    }

    //    shiro的安全管理器
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(loginRealm());
        securityManager.setCacheManager(cacheManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    //    开启shiro权限注解
    @Bean
    public AuthorizationAttributeSourceAdvisor advisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }


}
*/
