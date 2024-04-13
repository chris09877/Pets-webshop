package chris.ilg.dierenwinkel.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private int id;

    private String mail;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Integer id, String mail, String password, Collection<? extends GrantedAuthority> authorities) {
        this.mail = mail;
        this.password = password;
        this.id = id;
        this.authorities = authorities;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getId() {
        return id;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
