package com.woniuxy.config;

import com.woniuxy.component.CustomerRealm;
import com.woniuxy.filter.JwtFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public CustomerRealm realm(){
        CustomerRealm realm = new CustomerRealm();
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//        matcher.setHashAlgorithmName("md5");
//        matcher.setHashIterations(1024);
//        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/user/login","anon");

        // 使用自定义的filter过滤器：
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("jwtFilter",new JwtFilter());

        filterChainDefinitionMap.put("/user/logout","logout");
        //        目前还不清楚前后端分离之后，怎么拦截，所以直接全部放行
        filterChainDefinitionMap.put("/**","jwtFilter");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

////        设置阻拦之后跳转的界面
//        shiroFilterFactoryBean.setLoginUrl("/page/login.html");

        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        return shiroFilterFactoryBean;
    }
}
