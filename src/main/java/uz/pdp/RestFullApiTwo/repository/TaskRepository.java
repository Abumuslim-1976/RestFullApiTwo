package uz.pdp.RestFullApiTwo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.RestFullApiTwo.Entity.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    boolean existsByNameAndLanguageId(String name, Integer language_id);
    boolean existsByTextAndLanguageId(String text, Integer language_id);
}
