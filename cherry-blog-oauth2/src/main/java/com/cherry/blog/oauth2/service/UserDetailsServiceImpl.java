package com.cherry.blog.oauth2.service;


import com.cherry.blog.entities.SysMenu;
import com.cherry.blog.entities.SysUser;
import com.cherry.blog.feign.IFeignSystemController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // 不一定不要少了
public class UserDetailsServiceImpl implements UserDetailsService {

    //检查启动类注解 @EnableFeignClients
    @Autowired
    private IFeignSystemController feignSystemController;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        // 1. 判断用户名是否为空
        if(StringUtils.isEmpty(username)) {
            throw new BadCredentialsException("用户名不能为空");
        }
        // 2. 通过用户名查询数据库中的用户信息
        System.out.println("start --------------------------------");
        System.out.println("正在根据用户名查询用户信息。。。");
        System.out.println("feignSystemController信息：" + feignSystemController);
        SysUser sysUser = feignSystemController.findUserByUsername(username);
        /*SysUser sysUser = new SysUser();
        sysUser.setId("9");  //模拟数据
        sysUser.setUsername("admin");
        sysUser.setPassword("$2a$10$2c6uqCzY3ObyCBU7WnY/AORFVU6ZAR.JfUnsogxX3ixgsszCJeiWW");
        sysUser.setNickName("小王");
        sysUser.setImageUrl("https://weiliblog.oss-cn-beijing.aliyuncs.com/article/20220127/59ac4b63ea1e4cedba86eb1b71cb8c04.jpg");
        sysUser.setMobile("18888888888");
        sysUser.setEmail("1211840085@qq.com");
        sysUser.setIsAccountNonExpired(1);
        sysUser.setIsAccountNonLocked(1);
        sysUser.setIsCredentialsNonExpired(1);
        sysUser.setIsEnabled(1);*/
        System.out.println("userDetails查询到的用户信息");
        System.out.println("end --------------------------------");
        if(sysUser == null) {
            throw new BadCredentialsException("用户名或密码错误");
        }
        // 3. 通过用户id去查询数据库的拥有的权限信息
        List<SysMenu> menuList =
                feignSystemController.findMenuByUserId(sysUser.getId());
        // 4. 封装权限信息（权限标识符code）
        List<GrantedAuthority> authorities = null;
        if(CollectionUtils.isNotEmpty(menuList)) {
            authorities = new ArrayList<>();
            for(SysMenu menu: menuList) {
                // 权限标识
                String code = menu.getCode();
                authorities.add(new SimpleGrantedAuthority(code));
            }
        }
        // 5. 构建UserDetails接口的实现类JwtUser对象
        JwtUser jwtUser = new JwtUser(
                sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(),
                sysUser.getNickName(), sysUser.getImageUrl(), sysUser.getMobile(),
                sysUser.getEmail(),
                sysUser.getIsAccountNonExpired(), sysUser.getIsAccountNonLocked(),
                sysUser.getIsCredentialsNonExpired(), sysUser.getIsEnabled(),
                authorities );
        return jwtUser;
    }


}
