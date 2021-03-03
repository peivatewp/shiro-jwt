package com.woniuxy.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.woniuxy.component.JwtUtil;
import com.woniuxy.dto.Result;
import com.woniuxy.dto.StatusCode;
import com.woniuxy.entity.User;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WP
 * @since 2021-03-02
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserMapper userMapper;


    @PostMapping("login")
    public Result login(@RequestBody UserVO userVO) throws UnsupportedEncodingException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        System.out.println(userVO+"---------");
        queryWrapper.eq("username",userVO.getUsername());
        User userDB = userMapper.selectOne(queryWrapper);
        if (!ObjectUtils.isEmpty(userDB)) {
            // 使用MD5加密
            Md5Hash md5Hash = new Md5Hash(userVO.getPassword(),userDB.getSalt(),1024);
            String md5Password = md5Hash.toHex();
            // 判断密码：
            if (md5Password.equals(userDB.getPassword())) {
                // 使用jwt
                HashMap<String, String> map = new HashMap<>();
                map.put("username",userVO.getUsername());
                String JwtToken = JwtUtil.createToken(map);
                return new Result(true, StatusCode.OK,"登录成功",JwtToken);
            }else {
                return new Result(false,StatusCode.ERROR,"密码错误",null);
            }
        }
        return new Result(false,StatusCode.ERROR,"用户为注册",null);
    }



}

