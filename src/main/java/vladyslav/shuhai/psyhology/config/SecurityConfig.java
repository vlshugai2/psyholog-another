package vladyslav.shuhai.psyhology.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import vladyslav.shuhai.psyhology.security.JwtConfigure;
import vladyslav.shuhai.psyhology.security.JwtTokenTool;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * This variable for save jwtTokenTool.
     */
    @Autowired
    @Lazy
    private JwtTokenTool jwtTokenTool;

    /**
     * This is method need for authentication.
     * @return authentication bean
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * This is method which gives access to certain pages
     * if you have ROLE_ADMIN.
     * @param http security
     * @throws Exception
     */
    @Override
    protected final void configure(final HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/admin/register","/admin/login", "/upload","/files","/user/create","/news/subscribe")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/adminPanel/addEvent")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.PUT, "/ticket/reservePlace").permitAll()

                .antMatchers("/img/**").permitAll()

                .anyRequest().hasAnyRole("ADMIN")
                .and()
                .apply(new JwtConfigure(jwtTokenTool));
    }

    /**
     * This is method which set allowed methods, origins, headers.
     * @return source
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays
                .asList("GET", "POST", "OPTIONS",
                        "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays
                .asList("X-Requested-With", "Origin",
                        "Content-Type", "Accept", "Authorization"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * This is configuration for swagger.
     * @param web
     * @throws Exception
     */
    @Override
    public final void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}