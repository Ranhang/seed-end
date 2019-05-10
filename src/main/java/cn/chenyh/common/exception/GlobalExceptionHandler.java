package cn.chenyh.common.exception;

import cn.chenyh.common.base.Response;
import cn.chenyh.common.base.ResponseResult;
import cn.chenyh.common.enums.ResponseResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
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
        return  Response.failure(ResponseResultEnum.LOGINNAME_NULL);
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
        return  Response.failure(ResponseResultEnum.LOGINNAME_NULL);
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
        return  Response.failure(ResponseResultEnum.LOGINNAME_NULL);
    }

}