package com.woniuxy.mapper;

import com.woniuxy.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WP
 * @since 2021-03-02
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT r.rolename \n" +
            "FROM t_user AS u\n" +
            "JOIN t_user_role AS ur\n" +
            "ON u.id = ur.uid\n" +
            "JOIN t_role AS r\n" +
            "ON r.id = ur.rid\n" +
            "WHERE u.username=#{username}")
    public List<Role> findRoleByUsername(String username);

}
