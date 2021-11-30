package telran.java38.israGuru.security.configuration;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityAuthorizationConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.POST, "/account/guide");
		web.ignoring().antMatchers(HttpMethod.GET, "/account/guide/{guideId}");
		web.ignoring().antMatchers(HttpMethod.POST, "/account/user");
		web.ignoring().antMatchers(HttpMethod.POST, "/account/user");
		web.ignoring().antMatchers(HttpMethod.POST, "/account/login/guide/{login}");
		web.ignoring().antMatchers(HttpMethod.POST, "/account/login/user/{login}");
				
		web.ignoring().antMatchers(HttpMethod.GET, "/event/{id}");
		web.ignoring().antMatchers(HttpMethod.GET, "/event/guide/{guideId}");
		web.ignoring().antMatchers(HttpMethod.GET, "/event/city/{city}");
		web.ignoring().antMatchers(HttpMethod.GET, "/event/date/{date}");
		web.ignoring().antMatchers(HttpMethod.GET, "/event/language/{language}");
		web.ignoring().antMatchers(HttpMethod.GET, "/event/level/{level}");		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.httpBasic();
		http.csrf().disable();
		http.cors();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
			.antMatchers(HttpMethod.PUT, "/account/guide/{guideId}").access("#guideId == authentication.name")
			.antMatchers(HttpMethod.PUT, "/account/guide/password/{guideId}").access("#guideId == authentication.name")
			.antMatchers(HttpMethod.GET, "/account/guide/password/{guideId}").access("#guideId == authentication.name")
			.antMatchers(HttpMethod.DELETE, "/account/guide/{guideId}").access("#guideId == authentication.name or hasRole('ADMINISTRATOR')")
			.antMatchers(HttpMethod.PUT, "/account/user/{userId}").access("#userId == authentication.name")
			.antMatchers(HttpMethod.PUT, "/account/user/password/{userId}").access("#userId == authentication.name")
			.antMatchers(HttpMethod.DELETE, "/account/user/{userId}").access("#userId == authentication.name or hasRole('ADMINISTRATOR')")		
		
			.antMatchers(HttpMethod.PUT, "/event/{guideId}").access("#guideId == authentication.name")
			.antMatchers(HttpMethod.PUT, "/event/{id}").access("@customSecurity.checkEventAuthority(#id, authentication.name)")
			.antMatchers(HttpMethod.PUT, "/event/{id}/activity").access("@customSecurity.checkEventAuthority(#id, authentication.name) and hasRole('VERIFIEDGUIDE')")
			.antMatchers(HttpMethod.GET, "/event/aktive/{guideId}").access("#guideId == authentication.name")
			.antMatchers(HttpMethod.GET, "/event/past/{guideId}").access("#guideId == authentication.name")
			.antMatchers(HttpMethod.GET, "/event/draft/{guideId}").access("#guideId == authentication.name")
			.antMatchers(HttpMethod.DELETE, "/event/{id}").access("@customSecurity.checkEventAuthority(#id, authentication.name) or hasRole('ADMINISTRATOR')")				
			
			.anyRequest().authenticated();
		
		
	}

}



