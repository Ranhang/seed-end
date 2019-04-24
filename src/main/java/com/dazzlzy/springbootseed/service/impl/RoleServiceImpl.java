package com.dazzlzy.springbootseed.service.impl;

import com.dazzlzy.common.exception.BusinessException;
import com.dazzlzy.springbootseed.mapper.user.RoleMapper;
import com.dazzlzy.springbootseed.model.user.Role;
import com.dazzlzy.springbootseed.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 角色Service实现类
 *
 * @author dazzlzy
 * @date 2018/5/27
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleMapper roleMapper;

    @Override
    public void addRole(Role role) throws ParseException {
        Date now = new Date();
        validateRole(role);
        role.setCreateTime(now);
        role.setLastUpdateTime(now);
        roleMapper.insert(role);
    }

    @Override
    public void delete(List<Integer> ids) {
       if(!CollectionUtils.isEmpty(ids)){
           ids.forEach(id -> roleMapper.deleteByPrimaryKey(id));
       }
    }

    @Override
    public void update(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public Role queryByIdOrName(Long id, String name) {
        return roleMapper.queryByIdOrName(id, name);
    }

    @Override
    public List<Role> queryByPage(Role role) {
        return roleMapper.queryByPage(role);
    }

    /**
     * 校验Role
     *
     * @param role 角色
     */
    private void validateRole(Role role) {
        if (role == null) {
            throw new BusinessException("角色不存在");
        }
        if (StringUtils.isBlank(role.getName())) {
            throw new BusinessException("角色名不能为空");
        }
        if (StringUtils.isBlank(role.getId()+"")) {
            throw new BusinessException("角色编码不能为空");
        }
    }
}
