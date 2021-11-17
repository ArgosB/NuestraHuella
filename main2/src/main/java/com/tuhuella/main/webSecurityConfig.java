package com.tuhuella.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tuhuella.main.services.UserDetailsServiceImpl;

import com.tuhuella.main.services.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
	        .antMatchers("/user/sign-up").permitAll()
                .antMatchers("/user/login").permitAll()
	        .antMatchers("/pet*").permitAll()

                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/logincheck")

                .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index")
               // .failureUrl("/login")
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout").permitAll()
                .permitAll()
                .and().csrf().disable();

    }
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //Crea el encriptador de contraseñas	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
//El numero 6 representa que tan fuerte quieres la encriptacion.
//Se puede en un rango entre 4 y 31. 
//Si no pones un numero el programa utilizara uno aleatoriamente cada vez
//que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    private UserService usersService;

    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
   private UserDetailsServiceImpl userDetailsService;


}