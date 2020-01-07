package cn.chenxun.common.enums;

import lombok.Getter;

/**
 * 运行环境枚举
 *
 * @author chenxun
 * @date 2020/01/06
 */
@Getter
public enum EnvironmentEnum {

    /**
     * 开发环境
     */
    DEV("dev"),
    /**
     * 生产环境
     */
    PROD("prod"),;

    private String name;

    EnvironmentEnum(String name) {
        this.name = name;
    }
    

}
