package com.cybersoft.cozastore.security;


import com.cybersoft.cozastore.filter.JwtFilter;
import com.cybersoft.cozastore.provider.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Class sẽ được quét khi spring boot chạy ở tầng config
@EnableWebSecurity // Custom Spring Secutiry
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    // Tạo ra chuẩn mã hóa password là lưu trữ ở IOC
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Autowired
    CustomAuthenProvider customAuthenProvider;

    // Chỉ định AuthenticationManager sử dụng CustomAuthenProvider
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable() // Tắt tấn công theo kiểu cross-site (gọi link theo nhiều trình duyệt khác nhau)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Không sử dụng session --- STATELESS: Không sử dụng cái gì hết
                .and()
                .authorizeHttpRequests()

                // các thằng dưới là con của thằng trên ----- Matchers là so sánh kiểm tra dữ liệu
                .antMatchers("/login/**").permitAll() //permitALl() : nếu có .per thì link này ai gọi cũng được tất cả (ALL)
                .antMatchers("/file/**").permitAll()
                .antMatchers("/cart/**").permitAll()
                .antMatchers("/size/**").permitAll()
                .antMatchers("/color/**").permitAll()

                .antMatchers("/product/**").permitAll()

//                .antMatchers(HttpMethod.GET, "/product/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/product/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/product/{idProduct}").hasRole("ADMIN")
//
//                .antMatchers(HttpMethod.DELETE, "/product").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/category").permitAll()
                 // link /product với phương thức POST phải có role ADMIN mới truy cập được

                .antMatchers(HttpMethod.GET, "/cart").hasRole("USER")

                .anyRequest().authenticated() // Tất cả các link còn lại cần phải chứng thực
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

