package cn.chenyh.common.enums;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cyh98
 * @Date: 2019/6/12 11:58
 * @Description: 登录名枚举
 */
@Getter
public enum  LoginNameEnum {

    ADMIN("admin");

    private String message;

    LoginNameEnum(String message){
        this.message = message;
    }


}
