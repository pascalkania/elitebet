package de.kania.elitebet.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // these matches will not go through the security filter (all above
        // static folder)
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/webjars/**", "/static/**", "/css/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("pascal").password("pascal").roles("USER", "ADMIN");
        auth.inMemoryAuthentication().withUser("julia").password("julia").roles("USER");
        auth.inMemoryAuthentication().withUser("meike").password("meike").roles("USER");
        auth.inMemoryAuthentication().withUser("jörn").password("jörn").roles("USER");
        auth.inMemoryAuthentication().withUser("johannes").password("johannes").roles("USER");
        auth.inMemoryAuthentication().withUser("tilmann").password("tilmann").roles("USER");
    }
}
