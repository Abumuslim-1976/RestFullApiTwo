package uz.pdp.RestFullApiTwo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.RestFullApiTwo.Entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {
}
