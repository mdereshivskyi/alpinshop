package test.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import test.project.config.jwt.JWTTokenFilterConfigurer;
import test.project.config.jwt.JWTTokenProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.antMatcher("/backpack/**").authorizeRequests().anyRequest().authenticated();
		http.antMatcher("/boots/**").authorizeRequests().anyRequest().authenticated();
		http.antMatcher("/equipment/**").authorizeRequests().anyRequest().authenticated();
		http.antMatcher("/helmet/**").authorizeRequests().anyRequest().authenticated();
		http.antMatcher("/jacket/**").authorizeRequests().anyRequest().authenticated();
		http.antMatcher("/pants/**").authorizeRequests().anyRequest().authenticated();
		
		
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN");
		
		http.authorizeRequests()
			.antMatchers("/user/**").hasAnyRole("ADMIN", "USER");

		http.apply(new JWTTokenFilterConfigurer(jwtTokenProvider));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
}
