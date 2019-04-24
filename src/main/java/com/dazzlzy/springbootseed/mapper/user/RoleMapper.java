package com.dazzlzy.springbootseed.mapper.user;


import com.dazzlzy.common.base.BaseMapper;
import com.dazzlzy.springbootseed.model.user.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    Role queryByIdOrName(Long id, String name);

    List<Role> queryByPage(Role role);
}