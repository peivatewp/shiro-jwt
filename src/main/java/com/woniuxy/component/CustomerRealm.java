package com.woniuxy.component;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.woniuxy.entity.Role;
import com.woniuxy.mapper.RoleMapper;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import javax.annotation.Resource;
import java.util.List;

public class CustomerRealm extends AuthorizingRealm {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }

    @SneakyThrows
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权进来了");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取token
        String token = (String) principalCollection.getPrimaryPrincipal();
        // 由于过滤器的原因：获取到token需要进行解密
        DecodedJWT decodedJWT = JwtUtil.decodedJWTToken(token);
        // 获取用户名：
        String username = decodedJWT.getClaim("username").asString();
        List<Role> roles = roleMapper.findRoleByUsername(username);
        roles.forEach(role -> {
            System.out.println(role.getRolename());
            simpleAuthorizationInfo.addRole(role.getRolename());
        });
        return simpleAuthorizationInfo;
    }


    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进来");
        //获取用户名
        String token = (String) authenticationToken.getPrincipal();
        // 由于过滤器的原因：获取到token需要进行解密
        DecodedJWT decodedJWT = JwtUtil.decodedJWTToken(token);
        String username = decodedJWT.getClaim("username").asString();
        //判断查出的结果
        if (!StringUtils.hasLength(username)) {
            throw new AuthenticationException("》》》认证信息失败《《《");

        }
        return new SimpleAuthenticationInfo(token,token,this.getName());
    }
}
