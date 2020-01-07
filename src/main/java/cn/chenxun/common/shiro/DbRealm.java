package cn.chenxun.common.shiro;

import cn.chenxun.common.consts.GlobalConst;
import cn.chenxun.common.jwt.JwtToken;
import cn.chenxun.common.redis.RedisClientTemplate;
import cn.chenxun.common.utils.JwtUtil;
import cn.chenxun.system.model.user.Resource;
import cn.chenxun.system.model.user.Role;
import cn.chenxun.system.model.user.User;
import cn.chenxun.system.service.IRoleService;
import cn.chenxun.system.service.IShiroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义shiro权限认证授权
 * Principals(身份)
 * Credentials(凭证)
 * Authorization（授权）
 * Authentication（认证/鉴权）
 *
 * @author chenxun
 * @date 2020/01/06
 */
@Slf4j
public class DbRealm extends AuthorizingRealm {

    /**
     * 由于在ShiroConfiguration中使用了new DbRealm()无参构造器，无法注入IShiroService，本处使用成员属性上@Autowired
     */
    @Autowired
    private IShiroService shiroService;

    @Autowired
    private IRoleService roleService;

    /**
     * 查询权限，授权
     * 此方法调用hasRole,hasPermission的时候才会进行回调.
     * <p>
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     *
     * @param principalCollection 身份集合
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("DbRealm.doGetAuthorizationInfo() shiro授权");

        Subject subject = SecurityUtils.getSubject();
        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout() (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，需要清除身份
        if (!subject.isAuthenticated()) {
            doClearCache(principalCollection);
            subject.logout();
            return null;
        }

        // 简单授权信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        String loginName = String.valueOf(SecurityUtils.getSubject().getPrincipal());
        User user = shiroService.login(loginName);

        if (user != null) {
            Set<String> roleIds = new HashSet<>();
            List<Role> roleList = user.getRoleList();
            Set<String> permissions = new HashSet<>();
            if (!CollectionUtils.isEmpty(roleList)) {
                roleList.forEach(role -> {
                    roleIds.add(String.valueOf(role.getId()));
                    Role rolePermissions = roleService.queryByIdOrName(role.getId(), null);
                    List<Resource> resourceList = rolePermissions.getResourceList();
                    if(!CollectionUtils.isEmpty(resourceList)){
                        resourceList.forEach(resource -> {
                            permissions.add(resource.getPermission());
                        });
                    }
                });
            }

            //添加角色
            authorizationInfo.addRoles(roleIds);
            // 添加权限
            authorizationInfo.addStringPermissions(permissions);
        }

        return authorizationInfo;
    }

    /**
     * 校验权限,认证
     *
     * @param authenticationToken 认证Token，只是用户密码认证(UsernamePasswordToken)
     * @return 认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("DbRealm.doGetAuthenticationInfo() shiro认证");
        AuthenticationInfo authenticationInfo = null;

        if (authenticationToken instanceof JwtToken) {
            log.info("Use UsernamePasswordToken for authentication");
            String token = (String) authenticationToken.getCredentials();
            // 解密获得account，用于和数据库进行对比
            String loginName = JwtUtil.getClaim(token, GlobalConst.ACCOUNT);

            // 帐号为空
            if (StringUtils.isBlank(loginName)) {
                throw new AuthenticationException("Token中帐号为空(The loginName in Token is empty.)");
            }

            User user = shiroService.login(loginName);
            if (user != null) {
                authenticationInfo = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
            }
        }

        return authenticationInfo;
    }


    @Override
    public boolean supports(AuthenticationToken token) {
        // 支持用户名/密码认证类型
        boolean support = token instanceof JwtToken;
        return token != null && support;
    }

}
