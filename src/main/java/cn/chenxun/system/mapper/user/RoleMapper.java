package cn.chenxun.system.mapper.user;


import cn.chenxun.common.base.BaseMapper;
import cn.chenxun.system.model.user.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    Role queryByIdOrName(@Param("id") Long id, String name);

    List<Role> queryByPage(Role role);
}