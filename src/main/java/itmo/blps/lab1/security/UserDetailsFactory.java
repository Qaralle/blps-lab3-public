package itmo.blps.lab1.security;

import itmo.blps.lab1.model.xml.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsFactory {

    public UserDetailsFactory() {
    }

    public static JavaUserDetails create(User user, Collection<GrantedAuthority> authorities) {
        return new JavaUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities,
                Boolean.TRUE
        );
    }


    //TODO сделать нормальную таблицу ролей
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> userRoles) {
        return userRoles.stream()
                .map(SimpleGrantedAuthority::new
                ).collect(Collectors.toList());
    }
}
