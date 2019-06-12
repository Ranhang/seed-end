package cn.chenyh.springbootseed.controller.user;

import cn.chenyh.common.base.Response;
import cn.chenyh.common.base.ResponseResult;
import cn.chenyh.springbootseed.model.user.Organization;
import cn.chenyh.springbootseed.model.user.User;
import cn.chenyh.springbootseed.service.IOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @Auther: cyh98
 * @Date: 2019/6/12 20:01
 * @Description:
 */

@Slf4j
@Api("组织")
@RestController
@RequestMapping(value = "organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final IOrganizationService organizationService;

    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public ResponseResult add(@RequestBody Organization organization) throws ParseException {
        return Response.success(organizationService.add(organization));
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation("删除")
    public ResponseResult delete(@RequestBody List<Integer> ids) {
        return Response.success(organizationService.delete(ids));
    }

    @PutMapping(value = "/update")
    @ApiOperation("更新")
    public ResponseResult update(@RequestBody Organization organization) {
        return Response.success(organizationService.update(organization));
    }

    @GetMapping(value = "/query")
    public ResponseResult queryByIdOrName(Long id, String name) {
        List<Organization> organizationList = organizationService.queryByIdOrName(id, name);
        return Response.success(organizationList);
    }
}
