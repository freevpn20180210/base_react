/*
package com.lyf.realm;

import com.lyf.common.LyfTools;
import com.lyf.common.SupperDao;
import com.lyf.po.sys.ZhUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private SupperDao dao;

//    public static void main(String[] args) {
//        Md5Hash md5Hash = new Md5Hash("123");
//        System.out.println(md5Hash.toString());
//    }

    // 认证
    // shiro认证根本:根据用户名查询出用户的账号密码等信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        ZhUser user = (ZhUser) dao.findOne("from ZhUser where userName=?1 and locked=false", new Object[]{userName});
        if (LyfTools.isNotEmpty(user)) {
            // SimpleAuthenticationInfo把user.getPwd()与UsernamePasswordToken中的密码(MD5加密)比较
            // 若相等,则认证通过;若不相等,则认证不通过,抛出IncorrectCredentialsException
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,
                    user.getPwd(), this.getClass().getName());
            return authenticationInfo;
        } else {
            // 返回为null表示不能找到用户名,认证不通过,抛出UnknownAccountException
            return null;
        }
    }

    // 授权--在访问被拦截的url时触发
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ZhUser user = (ZhUser) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取该用户的所有角色
        String sql = "SELECT c.roleName FROM zh_user a JOIN zh_user_role b ON a.id = b.userId JOIN zh_role c ON b.roleId = c.id WHERE userId = ?1";
        List<String> roleNameList = dao.findBySql(sql, new Object[]{user.getId()});
        //获取该用户的所有权限
        String sql1 = "SELECT d.url FROM zh_user a JOIN zh_user_role b ON a.id = b.userId JOIN zh_role_permission c ON b.roleId = c.roleId JOIN zh_permission d ON c.permissionId = d.id WHERE a.id = ?1 AND url <> ''";
        List<String> permissionUrlList = dao.findBySql(sql1, new Object[]{user.getId()});
        //把该用户的角色和权限添加到shiro里
        authorizationInfo.addRoles(roleNameList);
        authorizationInfo.addStringPermissions(permissionUrlList);
        return authorizationInfo;
    }

    //更新完权限后清除权限缓存使之即时生效
    public void clearAuthorizationInfo() {
        super.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
*/
