package org.hackathon.config;

import org.hackathon.security.JWTAuthenticationEntryPoint;
import org.hackathon.security.JWTAuthenticationFilter;
import org.hackathon.security.JWTTokenProvider;
import org.hackathon.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private JWTAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public JWTAuthenticationFilter authenticationFilter(JWTTokenProvider tokenProvider, PrincipalService service) throws Exception {
        return new JWTAuthenticationFilter(tokenProvider, service);
    }

    @Bean
    public JWTTokenProvider jwtTokenProvider() {
        return new JWTTokenProvider(securityProperties);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/token", "/user-signup").permitAll()
                .antMatchers(HttpMethod.GET, "/api/events", "/api/organizations").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationFilter(jwtTokenProvider(), principalService),
                UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
