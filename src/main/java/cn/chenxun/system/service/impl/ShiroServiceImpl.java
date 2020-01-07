package cn.chenxun.system.service.impl;

import cn.chenxun.common.enums.BooleanEnum;
import cn.chenxun.system.model.user.User;
import cn.chenxun.system.service.IShiroService;
import cn.chenxun.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;

/**
 * Shiro认证Service实现
 *
 * @author zhaozhenyao
 * @date 2018/5/10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShiroServiceImpl implements IShiroService {

    private final IUserService userService;


    @Override
    public User login(String loginName) {
        User user = userService.queryByIdOrName(loginName, null);
        //校验用户密码
        this.validateUserPassword(user);
        return user;
    }

    /**
     * 校验用户密码
     * @param user 用户
     */
    private void validateUserPassword(User user) {
        if (user == null) {
            // 用户不存在
            throw new UnknownAccountException();
        }
        if (BooleanEnum.NO.getValue() == user.getStatus()) {
            // 账户不可用
            throw new LockedAccountException();
        }
    }

    /**
     * 隐藏密码
     *
     * @param user 用户
     */
    private void hidePassword(User user) {
        user.setPassword("");
    }
}
