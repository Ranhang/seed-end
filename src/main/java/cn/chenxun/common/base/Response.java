package cn.chenxun.common.base;


import cn.chenxun.common.enums.ResponseResultEnum;
import cn.chenxun.common.exception.BusinessException;

/**
 * RESPONSERESULT生成器
 *
 * @author chenxun
 * @date 2018/4/1
 */
public class Response {

    /**
     * 生成返回结果
     *
     * @param code    返回编码
     * @param message 返回消息
     * @param data    返回数据
     * @param <T>     返回数据类型
     * @return 返回结果
     */
    public static <T> ResponseResult<T> generate(final int code, final String message, T data) {
        return new ResponseResult<>(code, false, message, data);
    }

    /**
     * 操作成功响应结果， 默认结果
     *
     * @return 操作成功的默认响应结果
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(ResponseResultEnum.SUCCESS.getCode(), true, ResponseResultEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 操作成功响应结果， 自定义数据及信息
     *
     * @param message 自定义信息
     * @param data    自定义数据
     * @param <T>     自定义数据类型
     * @return 响应结果
     */
    public static <T> ResponseResult<T> success(final String message, final T data) {
        return new ResponseResult<>(ResponseResultEnum.SUCCESS.getCode(), true, message, data);
    }

    /**
     * 操作成功响应结果，自定义数据，默认信息
     *
     * @param data 自定义数据
     * @param <T>  自定义数据类型
     * @return 响应结果
     */
    public static <T> ResponseResult<T> success(final T data) {
        return new ResponseResult<>(ResponseResultEnum.SUCCESS.getCode(), true, ResponseResultEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 操作成功响应结果，自定义信息，无数据
     *
     * @param message 自定义信息
     * @return 响应结果
     */
    public static <T> ResponseResult<T> success4Message(final String message) {
        return new ResponseResult<>(ResponseResultEnum.SUCCESS.getCode(), true, message, null);
    }

    /**
     * 操作失败响应结果， 默认结果
     *
     * @return 操作成功的默认响应结果
     */
    public static <T> ResponseResult<T> failure() {
        return new ResponseResult<>(ResponseResultEnum.FAIL.getCode(), false, ResponseResultEnum.FAIL.getMessage(), null);
    }

    /**
     * 操作失败响应结果， 自定义错误编码及信息
     *
     * @param code    自定义错误编码
     * @param message 自定义信息
     * @return 响应结果
     */
    public static <T> ResponseResult<T> failure(final int code, final String message) {
        return new ResponseResult<>(code, false, message, null);
    }

    /**
     * 操作失败响应结果， 自定义错误编码及信息
     *
     * @param code    自定义错误编码
     * @param message 自定义信息
     * @return 响应结果
     */
    public static <T> ResponseResult<T> failure(final int code, final String message, T data) {
        return new ResponseResult<>(code, false, message, data);
    }

    /**
     * 操作失败响应结果，自定义错误编码
     *
     * @param RESPONSERESULTENUM 自定义错误编码枚举
     * @return 响应结果
     */
    public static <T> ResponseResult<T> failure(final ResponseResultEnum RESPONSERESULTENUM) {
        return new ResponseResult<>(RESPONSERESULTENUM.getCode(), true, RESPONSERESULTENUM.getMessage(), null);
    }

    /**
     * 操作失败响应结果，自定义信息
     *
     * @param message 自定义信息
     * @return 响应结果
     */
    public static <T> ResponseResult<T> failure(final String message) {
        return new ResponseResult<>(ResponseResultEnum.FAIL.getCode(), false, message, null);
    }

    /**
     * 异常响应结果， 默认结果
     *
     * @return 操作成功的默认响应结果
     */
    public static <T> ResponseResult<T> error() {
        return new ResponseResult<>(ResponseResultEnum.SERVER_ERROR.getCode(), false, ResponseResultEnum.SERVER_ERROR.getMessage(), null);
    }

    /**
     * 异常响应结果， 自定义错误编码及信息
     *
     * @param code    自定义错误编码
     * @param message 自定义信息
     * @return 响应结果
     */
    public static <T> ResponseResult<T> error(final int code, final String message) {
        return new ResponseResult<>(code, false, message, null);
    }

    /**
     * 异常响应结果，自定义错误编码
     *
     * @param RESPONSERESULTENUM 自定义错误编码枚举
     * @return 响应结果
     */
    public static <T> ResponseResult<T> error(final ResponseResultEnum RESPONSERESULTENUM) {
        return new ResponseResult<>(RESPONSERESULTENUM.getCode(), false, RESPONSERESULTENUM.getMessage(), null);
    }

    /**
     * 业务异常响应结果
     *
     * @param be 业务异常
     * @return 响应结果
     */
    public static <T> ResponseResult<T> error(final BusinessException be) {
        return new ResponseResult<>(ResponseResultEnum.SERVER_ERROR.getCode(), false, be.getErrorMessage(), null);
    }

    /**
     * 异常响应结果，自定义信息
     *
     * @param message 自定义信息
     * @return 响应结果
     */
    public static <T> ResponseResult<T> error(final String message) {
        return new ResponseResult<>(ResponseResultEnum.SERVER_ERROR.getCode(), false, message, null);
    }

}
