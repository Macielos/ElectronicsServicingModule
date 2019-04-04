package esp.config;

import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String FRONTEND_URL = "http://localhost:4200";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//.anyRequest().permitAll();
				.antMatchers("/equipment", "/issue", "/comment").hasRole("USER")
				.anyRequest().authenticated().and()
				.httpBasic().and()
				.csrf().disable()  //csrfTokenRepository(getCsrfTokenRepository()); //FIXME doesn't work, although I check in browser that angular sets the token
				.cors();

	}

	private CookieCsrfTokenRepository getCsrfTokenRepository() {
		var repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
		repository.setCookiePath("/");
		return repository;
	}

	@Bean
	public UserDetailsService userDetailsService(UserConfig userConfig) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		UserDetails[] userDetails = userConfig.getUsers().stream()
				.map(user -> User.withUsername(user.getName())
						.passwordEncoder(encoder::encode)
						.password(user.getPassword())
						.roles("USER")
						.build()).toArray(UserDetails[]::new);

		return new InMemoryUserDetailsManager(userDetails);
	}
}
