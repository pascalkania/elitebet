package de.kania.elitebet.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // these matches will not go through the security filter (all above
        // static folder)
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**");
    }

    @Override
        protected void configure (HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/webjars/**", "/static/**", "/css/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username("pascal").password
                ("pascal").roles("USER", "ADMIN").build());
        auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username("julia").password
                ("julia").roles("USER").build());
        auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username("meike").password
                ("meike").roles("USER").build());
        auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username("jörn").password
                ("jörn").roles("USER").build());
        auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username("johannes").password
                ("johannes").roles("USER").build());
        auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username("tilmann").password
                ("tilmann").roles("USER").build());
    }
}
