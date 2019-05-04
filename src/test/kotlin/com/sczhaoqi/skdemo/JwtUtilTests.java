package com.sczhaoqi.skdemo;

import com.sczhaoqi.skdemo.pojo.dto.UserAccountDto;
import com.sczhaoqi.skdemo.utils.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author sczhaoqi
 * @date 2019/5/4 17:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUtilTests
{
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testTokenGenAndParse()
    {
        UserAccountDto userAccountDto = new UserAccountDto();
        userAccountDto.setRemember(true);
        userAccountDto.setUsername("test");
        userAccountDto.setExpired(false);
        userAccountDto.setLocked(false);
        userAccountDto.setEnabled(true);
        String token = jwtTokenUtil.createToken(userAccountDto);
        assert "test".equals(jwtTokenUtil.getUserAccount(token).getUsername());
    }
    @Test
    public void defaultPassword(){
        System.out.println(passwordEncoder.encode("sksystem"));
        System.out.println(passwordEncoder.encode("skadmin"));
    }
}
