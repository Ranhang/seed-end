package com.dazzlzy.springbootseed.controller.user;

import com.dazzlzy.common.base.Response;
import com.dazzlzy.common.base.ResponseResult;
import com.dazzlzy.springbootseed.model.user.User;
import com.dazzlzy.springbootseed.service.IUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @Auther: cyh98
 * @Date: 2019/4/17 18:26
 * @Description:
 */

@Slf4j
@Api("用户")
@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping(value = "/add")
//    @ApiOperation("新增")
    public ResponseResult add(@RequestBody User user) throws ParseException {
        userService.addUser(user);
        return Response.success();
    }

    @DeleteMapping(value = "/delete")
//    @ApiOperation("删除")
    public ResponseResult delete(@RequestBody List<Integer> ids) {
        userService.delete(ids);
        return Response.success();
    }

    @PutMapping(value = "/update")
//    @ApiOperation("更新")
    public ResponseResult update(@RequestBody User user) {
        userService.update(user);
        return Response.success();
    }

    @GetMapping(value = "/query")
    public ResponseResult queryByIdOrName(Long id, String name) {
        User user = userService.queryByIdOrName(id, name);
        return Response.success(user);
    }

    @PostMapping(value = "/queryByPage")
//    @ApiOperation("分页")
    public ResponseResult queryByPage(@RequestBody User user) {
        List<User> userList = userService.queryByPage(user);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println("--------------------------------");
        return Response.success(pageInfo);
    }
}
