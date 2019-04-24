package com.dazzlzy.springbootseed.mapper.user;

import com.dazzlzy.common.base.BaseMapper;
import com.dazzlzy.springbootseed.model.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> selectUserByIds(@Param("ids") List<Long> ids);

    User queryByIdOrName(@Param("id") Long userId, @Param("loginName") String loginName);

    List<User> queryByPage(User user);
}