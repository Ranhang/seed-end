package cn.chenyh.springbootseed.controller.user;

import cn.chenyh.common.base.Response;
import cn.chenyh.common.base.ResponseResult;
import cn.chenyh.common.utils.StringUtil;
import cn.chenyh.springbootseed.model.user.User;
import cn.chenyh.springbootseed.service.IUserService;
import cn.chenyh.springbootseed.vo.user.UserTableVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @ApiOperation("新增")
    public ResponseResult add(@RequestBody User user) throws ParseException {
        userService.addUser(user);
        return Response.success();
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation("删除")
    public ResponseResult delete(@RequestBody List<Integer> ids) {
        userService.delete(ids);
        return Response.success();
    }

    @PutMapping(value = "/update")
    @ApiOperation("更新")
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
    @ApiOperation("分页")
    public ResponseResult queryByPage(@RequestBody UserTableVo userTableVo) {
        List<UserTableVo> userList = userService.queryByPage(userTableVo);
        PageInfo<UserTableVo> pageInfo = new PageInfo<>(userList);
        return Response.success(pageInfo);
    }

    @PostMapping(value = "/login")
    @ApiOperation("分页")
    public ResponseResult login(String loginName, String password) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName,  password);
        //进行验证，这里可以捕获异常，然后返回对应信息
        subject.login(usernamePasswordToken);
        return Response.success();
        }
}
