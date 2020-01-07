package cn.chenxun.system.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUser extends AbstractVo{

    @NotNull(groups = Login.class, message = "登录名不能为空")
    private String loginName;

    @NotNull(groups = Login.class, message = "密码不能为空")
    private String password;


}
