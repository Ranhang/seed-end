package cn.chenxun.system.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@ToString
@Table(name = "user_role")
public class UserRole {
    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

}