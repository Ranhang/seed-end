package cn.chenyh.springbootseed.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
@Setter
@Getter
@ToString
public class Resource {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 资源介绍
     */
    private String description;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 父级资源id
     */
    private Long pid;

    /**
     * 排序
     */
    private Byte seq;

    /**
     * 资源类别
     */
    @Column(name = "resource_type")
    private Byte resourceType;

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

}