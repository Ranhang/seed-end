package cn.chenyh.springbootseed.service;

import cn.chenyh.springbootseed.model.user.Organization;
import cn.chenyh.springbootseed.model.user.Role;
import cn.chenyh.springbootseed.model.user.SysLog;

import java.text.ParseException;
import java.util.List;

/**
 * @Auther: cyh98
 * @Date: 2019/6/12 20:02
 * @Description:
 */
public interface ISysLogService {
    void add(SysLog sysLog) throws ParseException;

    Role queryByIdOrName(Long id, String name);

    List<Organization> queryByPage(Role role);
}
