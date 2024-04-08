package chris.ilg.dierenwinkel.Security;

import java.util.Arrays;
import java.util.Collection;

import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        super();
        this.userRepo = userRepo;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepo.findByMail(mail);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new CustomUserDetails(user.getMail(), user.getPassword(), authorities());
    }

    public Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

}
