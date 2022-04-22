package itmo.blps.lab1.service.impl;

import itmo.blps.lab1.model.xml.User;
import itmo.blps.lab1.repository.xml.RoleRepository;
import itmo.blps.lab1.repository.xml.UserRepository;
import itmo.blps.lab1.security.UserDetailsFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class XmlUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public XmlUserDetailService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        UserDetails userDetails = UserDetailsFactory.create(user,getAuthorities(user));
        return userDetails;
    }


    private Collection<GrantedAuthority> getAuthorities(User user){
        List<String> userRoles = user.getRoles();
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for(String ROLE : userRoles){
            Objects.requireNonNull(roleRepository.getByUsername(ROLE).orElse(null))
                    .getPrivileges().forEach(p->authorities.add(new SimpleGrantedAuthority(p.toUpperCase())));
        }

        return authorities;
    }
}
