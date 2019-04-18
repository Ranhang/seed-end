package com.dazzlzy.springbootseed.model.user;

import com.dazzlzy.common.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * 用户
 *
 * @author dazzlzy
 * @date 2018/5/19
 */
@Data
@Table(name = "sys_user")
public class User extends BaseEntity {

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty("创建时间")
    private String createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    @ApiModelProperty("修改时间")
    private String modifyTime;

    /**
     * 启用状态
     */
    @Column(name = "state_code")
    @ApiModelProperty("启用状态")
    private Integer stateCode;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 盐
     */
    @ApiModelProperty("盐")
    private String salt;

    /**
     * Email
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 座机
     */
    @ApiModelProperty("座机")
    private String phone;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String mobile;

    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    @Column(name = "last_login_time")
    private String lastLoginTime;

    /**
     * 角色列表
     */
    @ApiModelProperty("角色列表")
    @Transient
    private List<Role> roles;

    /**
     * 权限列表
     */
    @ApiModelProperty("权限列表")
    @Transient
    private List<Permission> permissions;

}