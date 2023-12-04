package kr.nadeuli.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.nadeuli.category.Role;
import kr.nadeuli.security.CustomUserDetailsService;
import kr.nadeuli.security.OAuth2LoginFailureHandler;
import kr.nadeuli.security.OAuth2LoginSuccessHandler;
import kr.nadeuli.service.oauth.impl.CustomOauth2MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
  //1. 인증및 권한을 부여, 보호하는 기능을 제공
  //1-1. credential(principal -> username, credential -> password) 방식을 사용
  //1-2. securityFilterChain을 기반으로 동작한다.
  //1-3. XML설정을 안하고 @Bean을 사용해서 유지관리에 용이하다.

  /* Spring Security 흐름
  *  1. 사용자의 로그인 정보,인증요청이 담긴 Http Request를 수신한다
  *  2. AuthenticationFilter가 요청을 가로챈다(Jwt를 사용하기때문에 JwtAuthenticationFilter를 커스텀)
  *  2-1. 가로챈 정보를 통해 UsernamePasswordAuthenticationToken의 인증용 객체를 생성한다.
  *  3-1. AuthenticationManager을
  *
  *
  *
  *
  * */

  ///Field
  //2. JWT가 유효한지 체크하는 인증필터
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  //3. UserDetailsService를 커스텀한 서비스
  private final CustomUserDetailsService customUserDetailsService;

  //4. 소셜로그인 OAuth2Login의 성공/실패에 대한 처리
  private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
  private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;

  //5. Oauth2MemberService를 커스텀한 서비스
  private final CustomOauth2MemberServiceImpl customOauth2MemberService;


  ///Method
  //6. SecurityFilterChain을 반환하는 메소드 생성
  //6-1. 보안설정에대해서 어떻게 처리해나갈지 흐름 제공
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        //7. CSRF(Cross-Site Request Forgery) 공격을 비활성화하여 보안문제를 방지하고 웹 애플리케이션의 안전성을 강화
        //7-1. csrf를 비활성화하기위해 AbstractHttpConfigurer를 disable
        .csrf(AbstractHttpConfigurer::disable)
        //8. HTTP 요청에 대한 접근 권한을 설정
        .authorizeHttpRequests((request) -> request
            //8-1. .permitAll()에 해당하는 URI는 인증되지않은 회원도 접근 가능
            .requestMatchers("/api/v1/auth/**","/javascript/**","/css/**","/images/**","/addMember","/index.html","/nadeulidelivery/**","/product/**","/nadeuliPay/**","/trade/**","/orikkiri/**","/orikkiriManage/**","/member/**","/dongNe/**").permitAll()
            //8-2. ADMIN만 접근가능
            .requestMatchers("/api/v1/admin/**").hasAnyAuthority(Role.ADMIN.name())
            //8-3. USER만 접근가능
            .requestMatchers("/api/v1/member/**").hasAnyAuthority(Role.USER.name())
            //8-4. 모든 요청에 대해 인증이 필요함
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
                         .defaultSuccessUrl("/index.html", true)  // OAuth2 로그인 성공 시 기본 이동 경로
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
        .logout(logout -> logout
            .logoutUrl("/api/v1/member/logout")  // 로그아웃 URL 지정
            .logoutSuccessHandler((request, response, authentication) -> {
              // SecurityContext에서 Authentication을 지워 로그아웃
              SecurityContextHolder.clearContext();

              // 쿠키 삭제
              Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, null);
              cookie.setPath("/");
              cookie.setMaxAge(0);
              response.addCookie(cookie);

              // 헤더에서도 삭제
              response.setHeader(HttpHeaders.AUTHORIZATION, "");

              // 로그아웃 성공 시 리다이렉트 또는 메시지 등 추가할 수 있음
              response.setStatus(HttpServletResponse.SC_OK);
            })
        );

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
//    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }



}

