package com.tuhuella.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tuhuella.main.services.UserDetailsServiceImpl;

import com.tuhuella.main.services.UserService;

@Configuration
@EnableWebSecurity
public class webSecurityConfig extends WebSecurityConfigurerAdapter{
	//Necesario para evitar que la seguridad se aplique a los resources
    //Como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/include/**","/templates", "/templates.fragments","/css/**","/fontawesome-free-5.15.4-web/**","/bootstrap/**","/icons/**","/img/**","/js/**","/layer/**"
    };
	
@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        .antMatchers("/","/index").permitAll()
	        .antMatchers("/admin*").access("hasRole('ADMIN')")
	        .antMatchers("/user*").permitAll()
	        .antMatchers("/pet*").permitAll()

                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/menu")
                .failureUrl("/pet/register")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //Crea el encriptador de contraseñas	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(6);
//El numero 6 representa que tan fuerte quieres la encriptacion.
//Se puede en un rango entre 4 y 31. 
//Si no pones un numero el programa utilizara uno aleatoriamente cada vez
//que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    UserService usersService;
	
    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}

}