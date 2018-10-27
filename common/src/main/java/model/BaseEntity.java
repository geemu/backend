package model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础Entity
 * @author 陈方明  cfmmail@sina.com
 * @since 2018-10-27 11:29
 */
@Data
public class BaseEntity implements Serializable {
    /** 序列化id **/
    private static final long serialVersionUID = 6881372365379147087L;
    /** 主键  id **/
    private Long id;
    /** 是否可用  0不可用  1可用 **/
    private Boolean isEnable;
    /** 是否未删除  0已删除  1未删除 **/
    private Boolean isNoDelete;
    /** 创建人 **/
    private String createUser;
    /** 创建时间 **/
    private Date createTime;
    /** 更新人 **/
    private String updateUser;
    /** 更新时间 **/
    private Date updateTime;
    /** 乐观锁 **/
    private Long version;
}
