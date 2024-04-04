package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;


    @Override
    public User saveUser(UserDto userDto) {
        User user = new User(passwordEncoder.encode(userDto.getPassword());
        return userRepo.save(user);

    }

    @Override
    public User getUserById(int id) {
        return userRepo.getById(id);
    }
}