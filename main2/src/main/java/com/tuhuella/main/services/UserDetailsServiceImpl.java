package com.tuhuella.main.services;

import java.util.ArrayList;
import java.util.List;

import com.tuhuella.main.entities.HumanUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tuhuella.main.entities.Authority;
import com.tuhuella.main.repositories.HumanUserRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    HumanUserRepository userRepository;
	
    @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
     //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
     com.tuhuella.main.entities.HumanUser appUser = userRepository.findByUsername(email).orElseThrow(() -> new UsernameNotFoundException("No existe email"));
		
    //Mapear nuestra lista de Authority con la de spring security 
    List grantList = new ArrayList();
    for (Authority authority: appUser.getAuthority()) {
        // ROLE_USER, ROLE_ADMIN,..
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
            grantList.add(grantedAuthority);
    }
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usuarioSession", appUser );

        //HumanUser user = new HumanUser(appUser.getEmail(), appUser.getPassword(), grantedAuthority );
        //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
    UserDetails user = (UserDetails) new User(appUser.getEmail(), appUser.getPassword(), grantList);
         return user;
    }
}