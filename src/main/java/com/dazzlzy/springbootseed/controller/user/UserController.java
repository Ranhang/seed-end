package com.dazzlzy.springbootseed.controller.user;

import com.dazzlzy.common.base.RESPONSE;
import com.dazzlzy.common.base.RESPONSERESULT;
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
    public RESPONSERESULT add(@RequestBody User user) throws ParseException {
        userService.addUser(user);
        return RESPONSE.success();
    }

    @DeleteMapping(value = "/delete")
    public RESPONSERESULT delete(@RequestBody List<Integer> ids) {
        userService.delete(ids);
        return RESPONSE.success();
    }

    @PutMapping(value = "/update")
    public RESPONSERESULT update(@RequestBody User user) {
        userService.update(user);
        return RESPONSE.success();
    }

    @GetMapping(value = "/query")
    public RESPONSERESULT queryByIdOrName(Long id,String name) {
        User user = userService.queryByIdOrName(id, name);
        return RESPONSE.success(user);
    }

    @PostMapping(value = "/queryByPage")
    public RESPONSERESULT queryByPage(@RequestBody User user) {
        List<User> userList = userService.queryByPage(user);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return RESPONSE.success(pageInfo);
    }
}
