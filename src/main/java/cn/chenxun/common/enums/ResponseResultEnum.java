package cn.chenxun.common.enums;

import lombok.Getter;

/**
 * BaseResult的code编码枚举
 *
 * @author chenxun
 * @date 2018/3/21
 */
@Getter
public enum ResponseResultEnum {
    /**
     * SUCCESS: 200 成功
     * FAIL: 400 失败
     * NOT_FOUND： 404 不存在
     * SERVER_ERROR: 500 网络服务异常
     */
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    NOT_FOUND(404, "不存在"),
    SERVER_ERROR(500, "服务异常"),
    LOGINNAME_ERROR(501, "登录名错误"),
    PASSWORD_ERROR(502, "密码错误"),
    PARAMETER_ERROR(1001, "参数错误"),
    AUTHORIZATION_ERROR(503, "没有权限");

    private int code;

    private String message;

    ResponseResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
