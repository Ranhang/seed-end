package cn.chenyh.springbootseed.mapper.user;


import cn.chenyh.common.base.BaseMapper;
import cn.chenyh.springbootseed.model.user.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    Role queryByIdOrName(Long id, String name);

    List<Role> queryByPage(Role role);
}