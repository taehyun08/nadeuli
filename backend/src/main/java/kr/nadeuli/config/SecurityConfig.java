package kr.nadeuli.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import kr.nadeuli.common.Role;
import kr.nadeuli.security.CustomAuthenticationManager;
import kr.nadeuli.security.CustomUserDetailsService;
import kr.nadeuli.security.OAuth2LoginFailureHandler;
import kr.nadeuli.security.OAuth2LoginSuccessHandler;
import kr.nadeuli.service.oauth.impl.CustomOauth2MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {


  //1. JWT인증필터를 가져옴
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  //2. 유저서비스를 가져옴
  private final CustomUserDetailsService customUserDetailsService;

  private final CustomAuthenticationManager customAuthenticationManager;
  private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
  private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
  private final CustomOauth2MemberServiceImpl customOauth2MemberService;


  //3. SecurityFilterChain을 반환하는 메소드 생성
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
              @Override
              public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("*"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Collections.singletonList("Authorization"));
                config.addExposedHeader("Authorization");
                config.setMaxAge(3600L); //1시간
                return config;
              }
        }))
        //CSRF(Cross-Site Request Forgery) 공격을 비활성화하여 보안문제를 방지하고 웹 애플리케이션의 안전성을 강화
        .csrf(AbstractHttpConfigurer::disable) // csrf를 비활성화하기위해 AbstractHttpConfigurer를 disable
        // HTTP 요청에 대한 접근 권한을 설정
        .authorizeHttpRequests((request) -> request
            .requestMatchers("/api/v1/auth/**","/javascript/**","/css/**","/images/**","/addMember","/index.html").permitAll()
            .requestMatchers("/api/v1/admin/**").hasAnyAuthority(Role.ADMIN.name())
            .requestMatchers("/api/v1/member/**").hasAnyAuthority(Role.USER.name())
            .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults()) // httpBasic 사용 X
        //세션관리 비활성화,상태를 저장하지 않는 세션을 사용하며
        //모든 요청은 세션에 의존하지 않는다. 이는 주로 토큰 기반의 인증을 사용할 때 사용
        .sessionManagement(
            manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2Login(oauth2Configurer -> oauth2Configurer
                         .loginPage("/index.html")  // OAuth2 로그인 페이지 설정
                         .userInfoEndpoint(userInfo -> userInfo
                             .userService(customOauth2MemberService))
                         .successHandler(oAuth2LoginSuccessHandler) // 동의하고 계속하기를 눌렀을 때 Handler 설정
                         .failureHandler(oAuth2LoginFailureHandler) // 소셜 로그인 실패 시 핸들러 설정
//                         .defaultSuccessUrl("/login.html", true)  // OAuth2 로그인 성공 시 기본 이동 경로
        )
        .formLogin(login -> login
            .loginPage("/user/login")  // 로그인 페이지 설정
            .loginProcessingUrl("/user/login")  // 로그인 처리 URL 설정
//            .usernameParameter("userId")  // 사용자 아이디 파라미터명 설정
//            .passwordParameter("password")  // 비밀번호 파라미터명 설정
//            .defaultSuccessUrl("/", true)  // 로그인 성공 시 기본 이동 경로
            .permitAll()  // 로그인 페이지 접근 허용
        )
        //사용자 인증을 처리
        //사용자의 인증을 검증하고 사용자 정보를 가져오는 역할
        //사용자의 JWT 토큰을 검증하고, 사용자를 인증
        .authenticationProvider(authenticationProvider()).addFilterBefore(
            jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .logout(Customizer.withDefaults());  // 로그아웃 설정

    // SecurityFilterChain 반환
    //비밀번호 인증 필터 클래스 제공

    System.out.println("시큐리티필터체인");
    return http.build();

  }

  //사용자의 인증을 처리하는 역할
  @Bean
  public AuthenticationProvider authenticationProvider() {
    System.out.println("프로바이더");
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //사용자 정보를 가져오는 데 사용될 UserDetailsService를 설정
    authenticationProvider.setUserDetailsService(customUserDetailsService);
    //비밀번호 암호화를 처리하는 데 사용될 PasswordEncoder를 설정
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  //비밀번호를 안전하게 저장하기 위해 비밀번호를 해시(암호화)하는 데 사용
  @Bean
  public PasswordEncoder passwordEncoder() {
    System.out.println("비밀번호 인코더");
    //BCrypt는  강력한 해시알고리즘 중 하나로, 사용자 비밀번호를 안전하게 저장하는 데 사용
    return new BCryptPasswordEncoder();
  }

  //Spring Security에서 사용자의 인증을 처리하고 인증된 사용자를 관리하는 데 사용

  // 커스텀 인증 필터
//  @Bean
//  public CustomAuthenticationProcessingFilter customAuthenticationProcessingFilter() {
//    CustomAuthenticationProcessingFilter filter = new CustomAuthenticationProcessingFilter("/login-process");
//    filter.setAuthenticationManager(customAuthenticationManager);
//    filter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler("/login"));
//    filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/"));
//    return filter;
//  }
  //4. RestController로 이동하여 단일인입점생성

}

