package cn.chenxun.common.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Auther: cyh98
 * @Date: 2019/4/18 09:53
 * @Description:
 */
@Getter
@Setter
@ToString
public class BaseEntity {

    private Long key;

    @Transient
    private Integer pageNum = 1;

    @Transient
    private Integer rows = 10;
}
