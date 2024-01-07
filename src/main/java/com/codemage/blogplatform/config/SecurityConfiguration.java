package com.codemage.blogplatform.config;

import com.codemage.blogplatform.config.filters.LoggingFilter;
import com.codemage.blogplatform.constants.Route;
import com.codemage.blogplatform.security.CustomAuthenticationProvider;
import com.codemage.blogplatform.service.JpaUserDetailsService;
import com.codemage.blogplatform.utils.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.COOKIES;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(new LoggingFilter(), AuthorizationFilter.class)
                .formLogin((form) ->
                        form.loginPage(Route.LOGIN)
                                .defaultSuccessUrl(Route.DASHBOARD)
                                .passwordParameter("password")
                                .usernameParameter("username")
                )
                .sessionManagement((session) -> session
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession)
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                )
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers(Route.DASHBOARD).hasAuthority(Roles.ADMIN)
                                .anyRequest().permitAll()
                )
                .logout((logout) -> logout
                        .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(COOKIES)))
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) {
        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new JpaUserDetailsService();
    }

}
