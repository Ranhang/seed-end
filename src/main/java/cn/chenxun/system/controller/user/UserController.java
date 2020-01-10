package cn.chenxun.system.controller.user;

import cn.chenxun.common.base.Response;
import cn.chenxun.common.base.ResponseResult;
import cn.chenxun.common.jwt.JwtToken;
import cn.chenxun.common.utils.JwtUtil;
import cn.chenxun.system.model.user.User;
import cn.chenxun.system.service.IUserService;
import cn.chenxun.system.vo.user.LoginUser;
import cn.chenxun.system.vo.user.UserTableVo;
import cn.chenxun.system.vo.user.UserVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
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
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping("/api/user")
    @RequiresPermissions("system:user:add")
    @ApiOperation("新增")
    public ResponseResult add(@RequestBody User user) throws ParseException {
        userService.addUser(user);
        return Response.success();
    }

    @DeleteMapping("/api/user")
    @RequiresPermissions("system:user:delete")
    @ApiOperation("删除")
    public ResponseResult delete(@RequestBody List<Integer> ids) {
        userService.delete(ids);
        return Response.success();
    }

    @PutMapping("/api/user")
    @RequiresPermissions("system:user:put")
    @ApiOperation("更新")
    public ResponseResult update(@RequestBody User user) {
        userService.update(user);
        return Response.success();
    }

    @GetMapping("/api/user/info")
    @ApiOperation("查询当前用户信息")
    public ResponseResult<UserVo> queryByIdOrName() {
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        String loginName = String.valueOf(principal).replace("\"", "");
        UserVo user = userService.queryCuerrentUserInfo(loginName);
        return Response.success(user);
    }

    @GetMapping("/api/user")
    @RequiresPermissions("system:user:get")
    @ApiOperation("分页")
    public ResponseResult queryByPage(@RequestParam("pageNo") int pageNo,
                                      @RequestParam("pageSize") int pageSize) {
        UserTableVo userTableVo = new UserTableVo();
        userTableVo.setPageNum(pageNo);
        userTableVo.setRows(pageSize);
        List<UserTableVo> userList = userService.queryByPage(userTableVo);
        PageInfo<UserTableVo> pageInfo = new PageInfo<>(userList);
        return Response.success(pageInfo);
    }

    @PostMapping(value = "/api/user/login")
    @ApiOperation("登录")
    public ResponseResult<JwtToken> login(@Validated(LoginUser.Login.class) @RequestBody LoginUser user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        String token = JwtUtil.sign(user.getLoginName(), user.getPassword());
        JwtToken jwtToken = JwtToken.builder().token(token).build();
        subject.login(jwtToken);
        return Response.success(JwtToken.builder().token(jwtToken.getToken()).build());
    }

    @GetMapping(value = "/api/user/logout")
    @ApiOperation("登出")
    public ResponseResult logout() {
        //在这里执行退出系统前需要清空的数据
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return Response.success();
    }
}
