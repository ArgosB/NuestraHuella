package com.tuhuella.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tuhuella.main.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class webSecurityConfig extends WebSecurityConfigurerAdapter{
	//Necesario para evitar que la seguridad se aplique a los resources
    //Como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/include/**","/templates", "/templates.fragments","/css/**","/fontawesome-free-5.15.4-web/**","/bootstrap/**","/icons/**","/img/**","/js/**","/layer/**","/**"
    };
	
@Override
    protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers(resources).permitAll()
            .antMatchers("/","/index").permitAll()
            .antMatchers("/admin*").access("hasRole('ROLE_USER_DEFAULT')")
            .antMatchers("/user/sign-up").permitAll()
            .antMatchers("/user/login").permitAll()
            .antMatchers("/pet/*").access("hasRole('ROLE_USER_DEFAULT')")

            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/user/login")
            .loginProcessingUrl("/logincheck")

            .permitAll()
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/")
            // .failureUrl("/login")
            .and().logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout").permitAll()
            .permitAll()
            .and().csrf().disable();

    }
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //Crea el encriptador de contraseñas	
   /* @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

        return bCryptPasswordEncoder;
    }*/
	
    @Autowired
    private UserService userService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    }
