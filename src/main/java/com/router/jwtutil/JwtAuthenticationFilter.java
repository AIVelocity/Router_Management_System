package com.router.jwtutil;



import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.router.security.CustomUserDetailsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            if (jwtUtil.isTokenValid(token)) {

                Claims claims = jwtUtil.extractClaims(token);

                String username = claims.getSubject();
                Long userId = claims.get("userId", Long.class);
                Long roleId = claims.get("roleId", Long.class);
                String roleName = claims.get("roleName", String.class);

                // List To Linked hashmap for the multiple result return handling
                ObjectMapper mapper = new ObjectMapper();
                Map<String, List<String>> usecases =
                        mapper.convertValue(
                                claims.get("usecases"),
                                new TypeReference<>() {}
                        );

                // ✅ Store request attributes (optional but useful)
                request.setAttribute("userId", userId);
               // System.out.println("------------->"+userId);
                request.setAttribute("roleId", roleId);
                request.setAttribute("roleName", roleName);
                request.setAttribute("usecases", usecases);

                // ✅ Build authorities from JWT
                List<GrantedAuthority> authorities = new ArrayList<>();

                for (Map.Entry<String, List<String>> entry : usecases.entrySet()) {
                    String usecase = entry.getKey();
                    for (String perm : entry.getValue()) {
                        authorities.add(
                                new SimpleGrantedAuthority(usecase + "_" + perm)
                        );
                    }
                }

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username, null, authorities
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
