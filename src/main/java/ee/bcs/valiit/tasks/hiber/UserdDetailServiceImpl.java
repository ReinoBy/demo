package ee.bcs.valiit.tasks.hiber;

import ee.bcs.valiit.tasks.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserdDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepositoryHib userRepositoryHib;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = userRepositoryHib.findUserHibByUsername(username).getPassword();
        return User.withUsername(username)
                .password(password)
                .roles("USER").build();
    }

    @Transactional
    public Message newUser(UserHib userHib) {
        if (userRepositoryHib.countUserHibByUsernameEquals(userHib.getUsername()) >=1) {
            return new Message("Selline kasutaja on juba olemas");
        } else {
            String password = userHib.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            userHib.setPassword(encodedPassword);
            userRepositoryHib.save(userHib);
            return new Message("Kasutaja loodud");

        }
    }

}
