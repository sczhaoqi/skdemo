package com.sczhaoqi.skdemo.control;

import com.sczhaoqi.skdemo.pojo.ResponseBean;
import com.sczhaoqi.skdemo.pojo.dto.LoginDto;
import com.sczhaoqi.skdemo.pojo.dto.UserAccountDto;
import com.sczhaoqi.skdemo.pojo.po.User;
import com.sczhaoqi.skdemo.service.UserDetailsServiceImpl;
import com.sczhaoqi.skdemo.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author sczhaoqi
 * @date 2019/5/4 19:35
 */
@RestController
@RequestMapping("/api/account")
public class UserController
{
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResponseBean<String> login(@RequestBody LoginDto loginDto)
    {
        User user = userDetailsService.findUserByUsername(loginDto.getUsername());
        UserAccountDto userAccountDto = new UserAccountDto(user);
        userAccountDto.setRemember(loginDto.getRemember());
        if (user == null || !passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return ResponseBean.error("用户名或密码错误");
        }
        else {
            String token = jwtTokenUtil.createToken(userAccountDto);
            return ResponseBean.ok(token);
        }
    }

    @GetMapping("info")
    public ResponseBean<UserAccountDto> info(Authentication authentication){
        UserAccountDto user = (UserAccountDto) authentication.getPrincipal();
        if(user == null){
            return ResponseBean.error("请重新登陆");
        }
        User loginUser = userDetailsService.findUserByUsername(user.getUsername());
        if(loginUser!=null){
            return ResponseBean.ok(new UserAccountDto(loginUser));
        }
        return ResponseBean.error("获取用户信息失败");
    }
    @PostMapping("")
    public ResponseBean<UserAccountDto> create()
    {
        return null;
    }

    @PostMapping("demo")
    public String demo()
    {
        return "{" +
                "\"status\":" + "\"ok\"" + "," +
                "\"type\":" + "\"account\"" + "," +
                "\"currentAuthority\":" + "\"admin\"" + "," +
                "}";
    }
}
