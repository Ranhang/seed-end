package cn.chenyh.springbootseed.service;

import cn.chenyh.springbootseed.model.user.User;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Shiro认证Service
 *
 * @author zhaozhenyao
 * @date 2018/5/8
 */
public interface IShiroService {

    /**
     * shiro认证登陆，进行密码及合法性校验
     *
     * @return 若返回为null或者抛出异常，则认证失败，若返回有值，则认证成功
     */
    User login(UsernamePasswordToken usernamePasswordToken);
}
