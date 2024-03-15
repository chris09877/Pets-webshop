package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.User;

public interface UserService {
    public User saveUser(User user);
    public User getUserById(int id);
    public User getUserByMail(String mail);
}