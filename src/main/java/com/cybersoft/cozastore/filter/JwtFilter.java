package com.cybersoft.cozastore.filter;


import com.cybersoft.cozastore.util.JwtHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// dành cho việc giải mã token
// Tạo filter để hứng token mỗi khi người dùng gọi request

@Service //combonent cũng được
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    private Gson gson = new Gson();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Lấy token mà client truyền trên header (authorization)
        String headerValue = request.getHeader("Authorization");
        if(headerValue != null && headerValue.startsWith("Bearer ")){

            // Cắt chữ bearer để lấy token
            String token = headerValue.substring(7);
            jwtHelper.parserToken(token);
            String data = jwtHelper.parserToken(token);
            System.out.println("Kiem tra " + data);
            if(data != null && !data.isEmpty()){
                // Chứng thực hợp lệ, tạo chứng thực cho Security

                Type listType = new TypeToken<ArrayList<SimpleGrantedAuthority>>(){}.getType();
                List<SimpleGrantedAuthority> roles = gson.fromJson(data,listType);

//                List<GrantedAuthority> roles = new ArrayList<>();
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");

//                roles.add(roles);

                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken("","",roles);
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(user);

            }
        } else {
            // không hợp lệ
            System.out.println("Nội dung header không hợp lệ");
        }

        filterChain.doFilter(request, response);
    }
}
