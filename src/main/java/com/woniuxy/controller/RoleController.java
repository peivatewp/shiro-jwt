package com.woniuxy.controller;


import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.Role;
import com.woniuxy.mapper.RoleMapper;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WP
 * @since 2021-03-02
 */
@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {

    @Resource
    private RoleMapper roleMapper;


    @PostMapping("findAll")
    @RequiresRoles(logical = Logical.OR, value = {"", "董事长"})
    public Result findAll(Integer uid){
        List<Role> roles = roleMapper.selectList(null);
        return new Result(true, StatusCode.OK,"查询成功",roles);
    }
}

