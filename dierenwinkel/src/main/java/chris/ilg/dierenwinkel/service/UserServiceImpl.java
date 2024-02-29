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
    public User saveUser(User user) {
        return userRepo.save(user);
    }

}
