package cn.chenyh.springbootseed.mapper.user;

import cn.chenyh.common.base.BaseMapper;
import cn.chenyh.springbootseed.model.user.User;
import cn.chenyh.springbootseed.vo.user.UserTableVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserByIds(@Param("ids") List<Long> ids);

    User queryByIdOrName(@Param("id") Long userId, @Param("loginName") String loginName);

    List<UserTableVo> queryByPage(UserTableVo userTableVo);
}