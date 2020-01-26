package pl.connectis.electronicswebshop.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.connectis.electronicswebshop.web.auth.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailsService userDetailsService;
    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, MyUserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/registerCustomer/**",
                        "resources/templates/error", "resources/templates/index", "/basket/**").permitAll()
                .antMatchers().hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER")
                .antMatchers("/addProduct/**", "/allOrders**").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/registerEmployee/**").hasAnyRole("ADMIN")
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

}