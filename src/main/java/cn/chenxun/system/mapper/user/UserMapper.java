package cn.chenxun.system.mapper.user;

import cn.chenxun.common.base.BaseMapper;
import cn.chenxun.system.model.user.User;
import cn.chenxun.system.vo.user.UserTableVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserByIds(@Param("ids") List<Long> ids);

    User queryByIdOrName(@Param("loginName") String loginName, @Param("name") String name);

    List<UserTableVo> queryByPage(UserTableVo userTableVo);
}