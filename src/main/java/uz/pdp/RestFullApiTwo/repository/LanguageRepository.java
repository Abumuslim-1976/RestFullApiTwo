package uz.pdp.RestFullApiTwo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.RestFullApiTwo.Entity.Language;

public interface LanguageRepository extends JpaRepository<Language,Integer> {

    boolean existsByName(String name);
}
