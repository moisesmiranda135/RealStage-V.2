package com.salesianos.triana.dam.RealEstateV2.security;

import com.salesianos.triana.dam.RealEstateV2.security.jwt.JwtAccessDeniedHandler;
import com.salesianos.triana.dam.RealEstateV2.security.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthorizationFilter filter;
    private final JwtAccessDeniedHandler accessDeniedHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/propietario/{id}/").hasAnyRole("ADMIN","PROPIETARIO")
                .antMatchers(HttpMethod.GET, "/propietario/").authenticated()

                .antMatchers(HttpMethod.GET, "/vivienda/propietario").hasRole("PROPIETARIO")
                .antMatchers(HttpMethod.GET, "/vivienda/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/vivienda/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/vivienda/**").hasAnyRole("ADMIN","PROPIETARIO")
                .antMatchers(HttpMethod.DELETE, "/vivienda/{id}/inmobiliaria").hasAnyRole("ADMIN","PROPIETARIO","GESTOR")

                .antMatchers(HttpMethod.GET, "/inmobiliaria/{id}/").authenticated()
                .antMatchers(HttpMethod.GET, "/inmobiliaria/**").authenticated()
                .antMatchers(HttpMethod.POST, "/inmobiliaria/").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/inmobiliaria/{id}/").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/auth/register/gestor").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/auth/**").anonymous()

                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
