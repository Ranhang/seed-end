package cn.chenyh.springbootseed.service;

import cn.chenyh.springbootseed.model.user.Resource;
import cn.chenyh.springbootseed.model.user.User;

import java.util.List;

/**
 * @Auther: cyh98
 * @Date: 2019/6/12 20:28
 * @Description:
 */

public interface IResourceService {

    int add(Resource resource);

    int delete(List<Integer> ids);

    int update(Resource resourc);

    Resource queryByIdOrName(Long id, String name);
}
