package cn.chenxun.common.exception;

import cn.chenxun.common.base.Response;
import cn.chenxun.common.base.ResponseResult;
import cn.chenxun.common.enums.ResponseResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
/**
 * @Auther: cyh98
 * @Date: 2019/5/5 11:18
 * @Description:
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理请求对象属性不满足校验规则的异常信息
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = UnknownAccountException.class)
    public ResponseResult exception(HttpServletRequest request, UnknownAccountException exception) {
        return  Response.failure(ResponseResultEnum.LOGINNAME_ERROR);
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseResult exception(HttpServletRequest request, AuthorizationException exception) {
        return  Response.failure(ResponseResultEnum.AUTHORIZATION_ERROR);
    }
    /**
     * 处理请求对象属性不满足校验规则的异常信息
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = LockedAccountException.class)
    public ResponseResult exception(HttpServletRequest request, LockedAccountException exception) {
        return  Response.failure(ResponseResultEnum.LOGINNAME_ERROR);
    }

    /**
     * 处理请求对象属性不满足校验规则的异常信息
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public ResponseResult exception(HttpServletRequest request, IncorrectCredentialsException exception) {
        return  Response.failure(ResponseResultEnum.PASSWORD_ERROR);
    }

    /**
     * 用于处理参数校验错误
     * @Validated校验失败时会抛出MethodArgumentNotValidException异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult methodArgumentValidExceptionHandler(MethodArgumentNotValidException e) throws Exception {
        return  Response.failure(ResponseResultEnum.PARAMETER_ERROR);
    }

}