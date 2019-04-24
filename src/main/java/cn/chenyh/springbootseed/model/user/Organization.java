package cn.chenyh.springbootseed.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
@Setter
@Getter
@ToString
public class Organization {
    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 组织名
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 编号
     */
    private String code;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父级主键
     */
    private Long pid;

    /**
     * 排序
     */
    private Byte seq;

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
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 上次更新人
     */
    @Column(name = "last_updator")
    private String lastUpdator;

    /**
     * 上次更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
}