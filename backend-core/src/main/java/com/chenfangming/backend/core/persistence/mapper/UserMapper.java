package com.chenfangming.backend.core.persistence.mapper;
import com.chenfangming.backend.core.persistence.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据库操作
 * @author fangming.chen
 * @since 2018-08-28 15:10:
 * Email cfmmail@sina.com
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户,未删除的用户
     * @param name 用户名
     * @return 用户
     */
    User selectByName(String name);
}
