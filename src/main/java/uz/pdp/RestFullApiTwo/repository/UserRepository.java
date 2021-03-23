package uz.pdp.RestFullApiTwo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.RestFullApiTwo.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Integer id);
}
