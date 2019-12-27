package client.security;

import client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final String url = "http://localhost:8080/rest/user/login/";
    private RestTemplate restTemplate;

    @Autowired
    public UserDetailsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin", "12344"));
        return restTemplate.getForObject(url + s, User.class);
    }
}
