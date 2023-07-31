package pe.roalwh.exSpringBoot.config;

import lombok.RequiredArgsConstructor;

import pe.roalwh.exSpringBoot.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

  private final UserDetailService userService;

  // h2 DB 사용시 h2 DB 접근설정
  // @Bean
  // public WebSecurityCustomizer configure() {
  // return (web) -> web.ignoring()
  // .requestMatchers(toH2Console())
  // .requestMatchers("/static/**");
  // }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/login", "/signup", "/user").permitAll()
            .anyRequest().authenticated())
        .formLogin((formlogin) -> formlogin
            .loginPage("/login")
            .defaultSuccessUrl("/articles"));
    // .logout((logout) -> logout.logoutSuccessUrl("/login")
       // .invalidateHttpSession(true));

    http.csrf((csrf) -> csrf.disable());

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
      UserDetailService userDetailService) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userService)
        .passwordEncoder(bCryptPasswordEncoder)
        .and()
        .build();
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
