package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(UserDto userDto) {
        User user = new User(userDto, passwordEncoder);
        return userRepo.save(user);

    }

    @Override
    public User getUserById(int id) {
        return userRepo.getById(id);
    }

    @Override
    public User getUserByMail(String mail) {
        return userRepo.findByMail(mail);
    }
}