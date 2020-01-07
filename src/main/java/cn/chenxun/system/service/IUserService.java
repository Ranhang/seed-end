package cn.chenxun.system.service;

import cn.chenxun.system.model.user.User;
import cn.chenxun.system.vo.user.UserTableVo;
import cn.chenxun.system.vo.user.UserVo;

import java.text.ParseException;
import java.util.List;

/**
 * 用户 Service
 *
 * @author zhaozhenyao
 * @date 2018/5/8
 */
public interface IUserService {

    /**
     * 根据用户ID或者用户名查询用户
     *
     * @param loginName   登录名
     * @param name 用户名
     * @return
     */
    User queryByIdOrName(String loginName, String name);

    /**
     * 更新用户
     * @param user 更新信息
     */
    void update(User user);


    /**
     * 删除用户
     * @param ids 批量删除用户
     */
    void delete(List<Integer> ids);

    /**
     * 根据用户ID集合查询用户信息（包括权限角色）
     *
     * @param userIds 用户ID集合
     * @return 用户信息
     */
    List<User> queryUserByIds(List<Long> userIds);

    /**
     * 新增用户
     *
     * @param user 用户信息
     */
    void addUser(User user) throws ParseException;

    /**
     * 带条件分页查询
     *
     * @param userTableVo 用户信息
     * @return 用户集合
     */
    List<UserTableVo> queryByPage(UserTableVo userTableVo);

    UserVo queryCuerrentUserInfo(String loginName);
}
