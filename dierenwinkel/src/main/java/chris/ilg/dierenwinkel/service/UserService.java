package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.User;

public interface UserService {
    public User saveUser(UserDto userDto);
    public User getUserById(int id);
}