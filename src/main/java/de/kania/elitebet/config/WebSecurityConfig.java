package de.kania.elitebet.config;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.websocket.server.ServerEndpoint;

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
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser(User.withUsername("pascal").password
                ("pascal").roles("USER", "ADMIN").passwordEncoder(delegatingPasswordEncoder::encode).build());
        auth.inMemoryAuthentication().withUser(User.withUsername("julia").password
                ("julia").roles("USER").passwordEncoder(delegatingPasswordEncoder::encode).build());
        auth.inMemoryAuthentication().withUser(User.withUsername("meike").password
                ("meike").roles("USER").passwordEncoder(delegatingPasswordEncoder::encode).build());
        auth.inMemoryAuthentication().withUser(User.withUsername("jörn").password
                ("jörn").roles("USER").passwordEncoder(delegatingPasswordEncoder::encode).build());
        auth.inMemoryAuthentication().withUser(User.withUsername("johannes").password
                ("johannes").roles("USER").passwordEncoder(delegatingPasswordEncoder::encode).build());
        auth.inMemoryAuthentication().withUser(User.withUsername("tilmann").password
                ("tilmann").roles("USER").passwordEncoder(delegatingPasswordEncoder::encode).build());
    }

}
