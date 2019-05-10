package cn.chenyh.springbootseed.service.impl;

import cn.chenyh.common.enums.BooleanEnum;
import cn.chenyh.common.utils.PasswordUtil;
import cn.chenyh.springbootseed.service.IShiroService;
import cn.chenyh.springbootseed.service.IUserService;
import cn.chenyh.springbootseed.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User login(Long userId, String userName, String password) {
        User user = userService.queryByIdOrName(userId, userName);
        //校验用户密码
        this.validateUserPassword(user, password);
        //清除用户密码
        this.hidePassword(user);
        return user;
    }

    /**
     * 校验用户密码
     *
     * @param user     用户
     * @param password 需要校验的密码
     */
    private void validateUserPassword(User user, String password) {
        if (user == null) {
            // 用户不存在
            throw new UnknownAccountException();
        }
        if (BooleanEnum.NO.getValue() == user.getStatus()) {
            // 账户不可用
            throw new LockedAccountException();
        }
        String passwordDb = user.getPassword();
        if (!passwordDb.equals(PasswordUtil.encrypt(password, user.getSalt()))) {
            //密码不正确
            throw new IncorrectCredentialsException();
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
