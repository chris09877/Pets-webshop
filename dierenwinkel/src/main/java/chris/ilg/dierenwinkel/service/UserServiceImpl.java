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

    @Override
    public User getUserById(int id) {
        return userRepo.getById(id);
    }

    @Override
    public User getUserByMail(String mail) {
        return userRepo.getUserByMail(mail);
    }
}