package cn.chenxun.system.service;

import cn.chenxun.system.model.user.User;

/**
 * 登陆验证
 */
public interface IShiroService {

    /**
     * shiro认证登陆，进行密码及合法性校验
     *
     * @param loginName 登陆名
     * @return 若返回为null或者抛出异常，则认证失败，若返回有值，则认证成功
     */
    User login( String loginName);
}
