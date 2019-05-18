package com.sczhaoqi.skdemo.security;

import com.sczhaoqi.skdemo.service.UserDetailsServiceImpl;
import com.sczhaoqi.skdemo.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author sczhaoqi
 * @date 2019/5/4 15:29
 */
@Component
@Slf4j
public class JwtAuthenticationFilter
        extends OncePerRequestFilter
{
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        String authHeader = request.getHeader(
                jwtTokenUtil.getTokenHeader());
        if (authHeader != null
//                && authHeader.startsWith(jwtTokenUtil.getTokenPrefix())
        ) {
//            final String authToken = authHeader.substring(jwtTokenUtil.getTokenPrefix().length());
            final String authToken = authHeader;
            // The part after "Bearer "

            String username = null;
            try {
                username = jwtTokenUtil.getUserAccount(authToken).getUsername();
            }
            catch (Exception ex) {
                log.error("token 解析失败", ex);
            }
            log.info("checking authentication " + username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.verifyToken(authToken)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    log.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
