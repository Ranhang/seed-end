package cn.chenyh.springbootseed.model.user;

import cn.chenyh.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class User extends BaseEntity {

    /**
     * 登陆名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码加密盐
     */
    private String salt;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 年龄
     */
    private Byte age;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 所属机构
     */
    @Column(name = "organization_id")
    private Integer organizationId;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 上次更新人
     */
    @Column(name = "last_updator")
    private String lastUpdator;

    /**
     * 上次更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 角色信息
     */
    @Transient
    private List<Role> roleList;

    /**
     * 资源信息
     */
    @Transient
    private List<Resource> resourceList;
}