package com.cherry.blog.system.feign;

import com.cherry.blog.entities.SysMenu;
import com.cherry.blog.entities.SysUser;
import com.cherry.blog.feign.IFeignSystemController;
import com.cherry.blog.system.service.ISysMenuService;
import com.cherry.blog.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 不要少了
public class FeignSystemController implements IFeignSystemController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return
     */
    @Override
    public SysUser findUserByUsername(String username) {
        return sysUserService.findByUsername(username);
    }

    /**
     * 通过用户id查询拥有权限
     * @param userId 用户id
     * @return
     */
    @Override
    public List<SysMenu> findMenuByUserId(String userId) {
        return sysMenuService.findByUserId(userId);
    }

}
