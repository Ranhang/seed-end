package cn.chenyh.springbootseed.model.user;

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
@Table(name = "sys_log")
public class SysLog {
    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 内容
     */
    @Column(name = "opt_content")
    private String optContent;

    /**
     * 客户端ip
     */
    @Column(name = "client_ip")
    private String clientIp;

    /**
     * 角色名
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}