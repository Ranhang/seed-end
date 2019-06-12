package cn.chenyh.common.shiro;

import cn.chenyh.common.utils.RedisTemplateUtil;
import cn.chenyh.springbootseed.model.user.Role;
import cn.chenyh.springbootseed.model.user.User;
import cn.chenyh.springbootseed.service.IShiroService;
import cn.chenyh.springbootseed.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
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
 * @author dazzlzy
 * @date 2018/5/26
 */
@Slf4j
@RequiredArgsConstructor
public class BaseRealm extends AuthorizingRealm {

    /**
     * 由于在ShiroConfiguration中使用了new BaseRealm()无参构造器，无法注入IShiroService，本处使用成员属性上@Autowired
     */
    @Autowired
    private  IShiroService shiroService;

    @Autowired
    private RedisTemplateUtil redisTemplateUtil;

    @Autowired
    private IUserService userService;

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
        log.info("BaseRealm.doGetAuthorizationInfo() shiro授权");

        // 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout() (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，需要清除身份
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            doClearCache(principalCollection);
            SecurityUtils.getSubject().logout();
            return null;
        }

        // 简单授权信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principalCollection.getPrimaryPrincipal();
        User user =  userService.queryByIdOrName(null, username);
        if (user != null) {
            Set<String> roleIds = new HashSet<>();
            List<Role> roleList = user.getRoleList();

            Set<String> resourceIds = new HashSet<>();
            if(!CollectionUtils.isEmpty(roleList)){
                roleList.forEach(role -> {
                    roleIds.add(String.valueOf(role.getId()));
                    if(!CollectionUtils.isEmpty(role.getResourceList())){
                        role.getResourceList().forEach(resource ->
                            resourceIds.add(String.valueOf(resource.getId()))
                        );
                    }
                });
            }

            //添加角色
            authorizationInfo.addRoles(roleIds);
            // 添加权限
            authorizationInfo.addStringPermissions(resourceIds);
        }

        return authorizationInfo;
    }

    /**
     * 校验权限,认证
     *
     * @param authenticationToken 认证Token，只是用户密码认证(UsernamePasswordToken)/ID密码认证(IdPasswordToken)
     * @return 认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("BaseRealm.doGetAuthenticationInfo() shiro认证");
        AuthenticationInfo authenticationInfo = null;
        if (authenticationToken instanceof UsernamePasswordToken) {
            log.info("Use UsernamePasswordToken for authentication");
            //用户密码校验
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            User user = shiroService.login(token);
            if (user != null) {
                authenticationInfo = new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), this.getName());
                redisTemplateUtil.set(user.getLoginName(), user);
            }
        }
        return authenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        // 支持用户名/密码、用户ID/密码的认证类型
        boolean support = token instanceof UsernamePasswordToken || token instanceof IdPasswordToken;
        return token != null && support;
    }

}
