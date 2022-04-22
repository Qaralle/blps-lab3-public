package itmo.blps.lab1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String REALM_NAME = "TED.com";

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/app/test").hasAuthority( "CAN_DEAD")
                .antMatchers("/api/app/create_conference").hasAuthority( "CREATE_CONFERENCE")
                .antMatchers("/api/app/get_reserved_time").hasAuthority( "GET_RESERVED_TIME")
                .antMatchers("/api/app/get_speaker_list").hasAuthority( "GET_SPEAKER_LIST")
                .antMatchers("/api/app/submit_speakers").hasAuthority( "SUBMIT_SPEAKER")
                .antMatchers("/api/app/confirmInvitation/{hash}").permitAll()
                .antMatchers("/YA-PIDOR").permitAll()
                .antMatchers("/api/app/userList").hasAuthority( "ACCESS_USER_LIST")
                .antMatchers("/api/app/create_speaker").hasAuthority( "ADD_SPEAKER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                    .realmName(REALM_NAME)
                    .authenticationEntryPoint(new TextBasicAuthenticationEntryPoint());



    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**"); // #3
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
