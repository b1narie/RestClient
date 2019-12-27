package client.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private AuthHandler authHandler;
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, AuthHandler authHandler, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.authHandler = authHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/**").hasAuthority("admin")
                .antMatchers("/user/**").hasAuthority("user")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authHandler)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/denied.html")
                .and()
                .csrf().disable();
    }
}
