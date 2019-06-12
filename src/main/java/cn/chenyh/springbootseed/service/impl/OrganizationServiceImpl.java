package cn.chenyh.springbootseed.service.impl;

import cn.chenyh.common.enums.BooleanEnum;
import cn.chenyh.springbootseed.mapper.user.OrganizationMapper;
import cn.chenyh.springbootseed.model.user.Organization;
import cn.chenyh.springbootseed.model.user.Role;
import cn.chenyh.springbootseed.service.IOrganizationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

/**
 * @Auther: cyh98
 * @Date: 2019/6/12 20:06
 * @Description:
 */
@Slf4j
@RequiredArgsConstructor
public class OrganizationServiceImpl implements IOrganizationService {

    private final OrganizationMapper organizationMapper;

    @Override
    public int add(Organization organization) throws ParseException {
        return organizationMapper.insertSelective(organization);
    }

    @Override
    public int delete(List<Integer> ids) {
        ids.forEach(id ->{
            organizationMapper.deleteByPrimaryKey(id);
        });
        return BooleanEnum.YES.getValue();
    }

    @Override
    public int update(Organization organization) {
        return organizationMapper.updateByPrimaryKeySelective(organization);
    }

    @Override
    public List<Organization> queryByIdOrName(Long id, String name) {
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(name);
        return organizationMapper.select(organization);
    }

    @Override
    public List<Organization> queryByPage(Organization organization) {
        return null;
    }
}
