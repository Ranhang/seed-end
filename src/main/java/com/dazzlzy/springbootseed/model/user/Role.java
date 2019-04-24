package com.dazzlzy.springbootseed.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class Role {
    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 排序号
     */
    private Byte seq;

    /**
     * 简介
     */
    private String description;

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
     * 用用权限
     */
    @Transient
    private List<Resource> resourceList;

}