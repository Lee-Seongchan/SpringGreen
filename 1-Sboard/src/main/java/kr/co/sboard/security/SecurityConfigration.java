package kr.co.sboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfigration {

	@Autowired
	private SecurityUserService service;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			// 사이트 위변조 방지 해제
			.csrf(CsrfConfigurer::disable) // 메서드 참조 연산자로 람다식을 간결하게 표현
			// 토큰방식으로 로그인처리하기때문에 폼방식 비활성
			.formLogin(config -> config.loginPage("/user/login")
					 					.defaultSuccessUrl("/")
										.failureUrl("/user/login?suceess=100")
										.usernameParameter("uid")
										.passwordParameter("pass"))
			//로그아웃 설정
				.logout(config -> config.invalidateHttpSession(true)
						.logoutUrl("/user/logout")
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutSuccessUrl("/user/login?suceess=200"))



			// 인가 권한 설정
			.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
								   .requestMatchers("/admin/**").hasAuthority("ADMIN")
								   .requestMatchers("/manager/**").hasAnyAuthority("ADMIN","MANAGER")
								   .requestMatchers("/user/**").permitAll()
								   .requestMatchers("/").authenticated()
					               .requestMatchers("/vendor/**", "/js/**", "/dist/**", "/data/**", "/less/**").permitAll());

		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}