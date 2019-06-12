package cn.chenyh.springbootseed.service;

import cn.chenyh.springbootseed.model.user.Organization;
import cn.chenyh.springbootseed.model.user.Role;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

/**
 * @Auther: cyh98
 * @Date: 2019/6/12 20:01
 * @Description:
 */

public interface IOrganizationService {

    int add(Organization organization) throws ParseException;

    int delete(List<Integer> ids);

    int update(Organization organization);

    List<Organization> queryByIdOrName(Long id, String name);

    List<Organization> queryByPage(Organization organization);
}
