package cn.chenyh.springbootseed.controller.user;

import cn.chenyh.common.base.Response;
import cn.chenyh.common.base.ResponseResult;
import cn.chenyh.springbootseed.model.user.Role;
import cn.chenyh.springbootseed.service.IRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @Auther: cyh98
 * @Date: 2019/4/17 18:24
 * @Description:
 */

@Slf4j
@Api("角色")
@RestController
@RequestMapping(value = "role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public ResponseResult add(@RequestBody Role role) throws ParseException {
        roleService.addRole(role);
        return Response.success();
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation("删除")
    public ResponseResult delete(@RequestBody List<Integer> ids) {
        roleService.delete(ids);
        return Response.success();
    }

    @PutMapping(value = "/update")
    @ApiOperation("修改")
    public ResponseResult update(@RequestBody Role role) {
        roleService.update(role);
        return Response.success();
    }

    @GetMapping(value = "/query")
    @ApiOperation("查询")
    public ResponseResult queryByIdOrName(Long id, String name) {
        Role role = roleService.queryByIdOrName(id, name);
        return Response.success(role);
    }

    @PostMapping(value = "/queryByPage")
    @ApiOperation("分页")
    public ResponseResult queryByPage(@RequestBody Role role) {
        List<Role> userList = roleService.queryByPage(role);
        PageInfo<Role> pageInfo = new PageInfo<>(userList);
        return Response.success(pageInfo);
    }


}
