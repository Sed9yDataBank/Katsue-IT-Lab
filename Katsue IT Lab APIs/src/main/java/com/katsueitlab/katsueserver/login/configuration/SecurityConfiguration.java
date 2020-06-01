package com.katsueitlab.katsueserver.login.configuration;

import com.katsueitlab.katsueserver.login.security.CustomIpAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomIpAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/anonymeMFXG63TZNVSQ/**").authenticated()
                .antMatchers("/**").permitAll()

                //Or We Can Simply Use >>>
                /*.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/anonymeMFXG63TZNVSQ/**").hasIpAddress("196.239.255.255")
                .anyRequest().authenticated()*/

                .and().formLogin().permitAll()
                .and().csrf().disable();
    }
}
