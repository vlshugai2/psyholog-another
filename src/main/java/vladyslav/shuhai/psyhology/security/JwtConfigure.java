package vladyslav.shuhai.psyhology.security;


import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private vladyslav.shuhai.psyhology.security.JwtTokenTool jwtTokenTool;

    public JwtConfigure(vladyslav.shuhai.psyhology.security.JwtTokenTool jwtTokenTool) {
        this.jwtTokenTool = jwtTokenTool;
    }

    @Override
    public void configure(HttpSecurity httpSecurity){
        vladyslav.shuhai.psyhology.security.JwtTokenFilter jwtTokenFilter = new vladyslav.shuhai.psyhology.security.JwtTokenFilter(jwtTokenTool);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}