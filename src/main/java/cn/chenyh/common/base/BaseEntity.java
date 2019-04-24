package cn.chenyh.common.base;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nutz.json.JsonField;

import javax.persistence.*;
import java.io.Serializable;

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
