/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaveApp.monolith.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author User
 */
//public class SecurityConfig{
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserRefrenceService refrenceService;

    @Autowired
    public SecurityConfig(UserRefrenceService refrenceService) {
        this.refrenceService = refrenceService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(refrenceService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/fonts/**", "/images/**", "/scss/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/**").permitAll()
                .antMatchers("/admin/**", "/adminManager/**", "/reAdminManager/**", "/adminPic/**", "/reAdminPic/**", "/adminEmployee/**", "/reAdminEmployee/**").hasAuthority("admin")
                .antMatchers("/pic/**", "/piclevel2/**", "/redirectpic/**", "/redirectpic1/**", "/redirectpicreject/**", "/picConcluded/**", "/picHistory/**").hasAuthority("pic")
                .antMatchers("/manager/**", "/managerConcluded/**", "/managerHistory/**", "/redirectmanager/**").hasAuthority("manager")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler((req, res, auth) -> {    //Success handler invoked after successful authentication
                    String athr = "";
                    String athrAdmin = "";
                    for (GrantedAuthority authority : auth.getAuthorities()) {
                        athr = authority.getAuthority();
                        if (authority.getAuthority().equals("admin")) {
                            athrAdmin = authority.getAuthority();
                        }
                    }
                    System.out.println(auth.getName());
                    if (athrAdmin.equals("admin")) {
                        res.sendRedirect("/admin");
                    }
                    else if (athr.equals("manager")) {
                        res.sendRedirect("/manager");
                    } else if (athr.equals("pic")) {
                        res.sendRedirect("/pic");
                    } else {
                        res.sendRedirect("/login");
                    }
                })
                .failureHandler((req, res, exp) -> {  // Failure handler invoked after authentication failure
                    String errMsg = "";
                    if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                        errMsg = "Invalid username or password.";
                    } else {
                        errMsg = "Unknown error - " + exp.getMessage();
                    }
                    req.getSession().setAttribute("message", errMsg);
                    res.sendRedirect("/login"); // Redirect user to login page with error message.
                })
                .permitAll()
                .and()
                .logout();
//        http
//                .authorizeRequests()
//                .antMatchers("/").hasAnyRole("admin")
//                .anyRequest()
//                .authenticated();
//                .and()
//                .formLogin() 
//                .loginPage("/login")
//                .successHandler((req,res,auth)->{    //Success handler invoked after successful authentication
//                    for (GrantedAuthority authority : auth.getAuthorities()) {
//                       System.out.println(authority.getAuthority());
//                    }
//                    System.out.println(auth.getName());
//                    res.sendRedirect("/"); // Redirect user to index/home page
//                 })
//                 .failureHandler((req,res,exp)->{  // Failure handler invoked after authentication failure
//                    String errMsg="";
//                    if(exp.getClass().isAssignableFrom(BadCredentialsException.class)){
//                       errMsg="Invalid username or password.";
//                    }else{
//                       errMsg="Unknown error - "+exp.getMessage();
//                    }
//                    req.getSession().setAttribute("message", errMsg);
//                    res.sendRedirect("/login"); // Redirect user to login page with error message.
//                 })
//                .permitAll();

    }

}
