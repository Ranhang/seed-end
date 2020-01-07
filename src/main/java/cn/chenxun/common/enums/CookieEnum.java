package cn.chenxun.common.enums;

import lombok.Getter;

/**
 * Cookie枚举
 *
 * @author chenxun
 * @date 2020/01/06
 */
@Getter
public enum CookieEnum {
    /**
     * REMEMBER_ME： Cookie中存储的REMEMBER_ME
     */
    REMEMBER_ME("rememberMe"),;

    private String value;

    CookieEnum(String value) {
        this.value = value;
    }
}
