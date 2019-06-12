package cn.chenyh.springbootseed.service.impl;

import cn.chenyh.common.enums.BooleanEnum;
import cn.chenyh.springbootseed.mapper.user.ResourceMapper;
import cn.chenyh.springbootseed.model.user.Resource;
import cn.chenyh.springbootseed.service.IResourceService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: cyh98
 * @Date: 2019/6/12 20:29
 * @Description:
 */
@RequiredArgsConstructor
public class ResourceServiceImpl implements IResourceService {

    private final ResourceMapper resourceMapper;


    @Override
    public int add(Resource resource) {
        return resourceMapper.insertSelective(resource);
    }

    @Override
    public int delete(List<Integer> ids) {
        ids.forEach(id->{
            resourceMapper.deleteByPrimaryKey(id);
        });
        return BooleanEnum.YES.getValue();
    }

    @Override
    public int update(Resource resource) {
        return resourceMapper.updateByPrimaryKey(resource);
    }

    @Override
    public Resource queryByIdOrName(Long id, String name) {
        return null;
    }
}
