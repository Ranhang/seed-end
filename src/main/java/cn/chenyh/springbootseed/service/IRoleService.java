package cn.chenyh.springbootseed.service;

import cn.chenyh.springbootseed.model.user.Role;

import java.text.ParseException;
import java.util.List;

/**
 * 角色Service接口
 *
 * @author dazzlzy
 * @date 2018/5/27
 */
public interface IRoleService {

    /**
     * 新增角色
     *
     * @param role 角色
     */
    void addRole(Role role) throws ParseException;

    void delete(List<Integer> ids);

    void update(Role role);

    Role queryByIdOrName(Long id, String name);

    List<Role> queryByPage(Role role);
}
