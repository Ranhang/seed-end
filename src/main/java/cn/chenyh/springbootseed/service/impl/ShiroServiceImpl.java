package cn.chenyh.springbootseed.service.impl;

import cn.chenyh.common.enums.BooleanEnum;
import cn.chenyh.common.enums.LoginNameEnum;
import cn.chenyh.common.utils.PasswordUtil;
import cn.chenyh.springbootseed.model.user.Role;
import cn.chenyh.springbootseed.service.IRoleService;
import cn.chenyh.springbootseed.service.IShiroService;
import cn.chenyh.springbootseed.service.IUserService;
import cn.chenyh.springbootseed.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private final IRoleService roleService;


    @Override
    public User login(UsernamePasswordToken token) {
        Long userId = null;
        String userName=token.getUsername();
        String password = String.valueOf(token.getPassword());
        User user = userService.queryByIdOrName(userId, userName);
        if(LoginNameEnum.ADMIN.getMessage().equals(user.getLoginName())){
            List<Role> roleList = roleService.queryAll(null);
            user.setRoleList(roleList);
        }
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
