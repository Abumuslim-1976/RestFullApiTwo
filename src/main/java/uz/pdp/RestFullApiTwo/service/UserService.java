package uz.pdp.RestFullApiTwo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.RestFullApiTwo.Entity.User;
import uz.pdp.RestFullApiTwo.payload.ApiResponse;
import uz.pdp.RestFullApiTwo.payload.UserDto;
import uz.pdp.RestFullApiTwo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    /**
     * User created
     *
     * @param userDto
     * @return
     */
    public ApiResponse createUser(UserDto userDto) {
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (exists)
            return new ApiResponse("This is such user email", false);

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("User created", true);
    }


    /**
     * get all users
     *
     * @return
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /**
     * get one user
     *
     * @param id
     * @return
     */
    public User getUser(Integer id) {
        Optional<User> userById = userRepository.findById(id);
        return userById.orElse(null);
    }


    /**
     * user delete
     *
     * @param id
     * @return
     */
    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return new ApiResponse("user not found", false);
        }
        return new ApiResponse("user deleted", true);
    }


    /**
     * User edit
     * @param id
     * @param userDto
     * @return
     */
    public ApiResponse editUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found",false);

        boolean exists = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
        if (exists)
            return new ApiResponse("This is such user email",false);

        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("User edited",true);
    }

}
