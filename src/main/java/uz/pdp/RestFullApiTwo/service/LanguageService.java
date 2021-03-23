package uz.pdp.RestFullApiTwo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.RestFullApiTwo.Entity.Language;
import uz.pdp.RestFullApiTwo.payload.ApiResponse;
import uz.pdp.RestFullApiTwo.payload.LanguageDto;
import uz.pdp.RestFullApiTwo.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;


    /**
     * new language created
     * @param languageDto
     * @return ApiResponse(message , success)
     */
    public ApiResponse createLanguage(LanguageDto languageDto) {
        boolean existsByName = languageRepository.existsByName(languageDto.getName());
        if (existsByName)
            return new ApiResponse("There is such language name",false);

        Language language = new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Language created",true);
    }


    /**
     * get all languages
     * @return languages list
     */
    public List<Language> getAllLanguage() {
        return languageRepository.findAll();
    }


    /**
     * get one language
     * @param id
     * @return
     */
    public Language getLanguage(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }


    /**
     * language deleted
     * @param id
     * @return
     */
    public ApiResponse deleteLanguage(Integer id) {
        try {
            languageRepository.deleteById(id);
        }catch (Exception e) {
            return new ApiResponse("ERROR !!! , language not found",false);
        }
        return new ApiResponse("language deleted",true);
    }


    /**
     * language edited
     * @param id
     * @param languageDto
     * @return
     */
    public ApiResponse editLanguage(Integer id,LanguageDto languageDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("language not found",false);

        boolean existsByName = languageRepository.existsByName(languageDto.getName());
        if (existsByName)
            return new ApiResponse("There is such language name",false);

        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("language edited",true);
    }

}
