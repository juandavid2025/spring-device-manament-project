package com.example.taller2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

//	@Autowired
//	private MyCustomUserDetailsService userDetailsService;
//
//	private PasswordEncoder passwordEncoder;
//
//	public SecurityConfig(PasswordEncoder passwordEncoder) {
//		this.passwordEncoder = passwordEncoder;
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}
//
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(passwordEncoder);
//		return authProvider;
//	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

//		httpSecurity.authorizeRequests().antMatchers("/**").authenticated().anyRequest().permitAll().and().formLogin()
//				.loginPage("/login").permitAll().defaultSuccessUrl("/").failureUrl("/login").and().logout().permitAll()
//				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

//		httpSecurity.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
//				.defaultSuccessUrl("/").and().formLogin().loginPage("/login").permitAll().and().logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").and();
//-----------------------------------------------------------------------------------------------------------------------

//		httpSecurity.authorizeRequests().antMatchers("/**").authenticated().anyRequest().permitAll().and().formLogin()
//				.and().logout().invalidateHttpSession(true).clearAuthentication(true)
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
//				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

		// httpSecurity.authorizeRequests().antMatchers("/**").authenticated().and().formLogin();
		httpSecurity.authorizeRequests().antMatchers("/**").permitAll();

		// intento login roles
//		httpSecurity.authorizeRequests().antMatchers("/admin").hasRole("ADMIN").antMatchers("/opera").hasRole("OPERA")
//				.anyRequest().authenticated().and().formLogin();

	}

	// intento login roles
//	@Override
//	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("springadmin").password(passwordEncoder().encode("admin123"))
//				.roles("ADMIN").and().withUser("springopera").password(passwordEncoder().encode("opera123"))
//				.roles("OPERA");
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}