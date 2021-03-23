package uz.pdp.RestFullApiTwo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.RestFullApiTwo.Entity.Language;
import uz.pdp.RestFullApiTwo.Entity.Task;
import uz.pdp.RestFullApiTwo.payload.ApiResponse;
import uz.pdp.RestFullApiTwo.payload.TaskDto;
import uz.pdp.RestFullApiTwo.repository.LanguageRepository;
import uz.pdp.RestFullApiTwo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    LanguageRepository languageRepository;


    public ApiResponse createTask(TaskDto taskDto) {
        boolean existsByNameAndLanguageId = taskRepository
                .existsByNameAndLanguageId(taskDto.getName(), taskDto.getLanguageId());
        if (existsByNameAndLanguageId)
            return new ApiResponse("This is such name and language", false);

        boolean existsByTextAndLanguageId = taskRepository
                .existsByTextAndLanguageId(taskDto.getText(), taskDto.getLanguageId());
        if (existsByTextAndLanguageId)
            return new ApiResponse("This is such text and language", false);

        Optional<Language> languageById = languageRepository.findById(taskDto.getLanguageId());
        if (!languageById.isPresent())
            return new ApiResponse("language not found", false);

        Task task = new Task();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setHelp(taskDto.getHelp());
        task.setLanguage(languageById.get());
        taskRepository.save(task);
        return new ApiResponse("Task created", true);
    }


    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }


    public Task getTask(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElseGet(Task::new);
    }


    public ApiResponse deleteTask(Integer id) {
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
            return new ApiResponse("task not found", false);
        }
        return new ApiResponse("task deleted", true);
    }


    public ApiResponse editTask(Integer id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found",false);

        boolean existsByNameAndLanguageId = taskRepository
                .existsByNameAndLanguageId(taskDto.getName(), taskDto.getLanguageId());
        if (existsByNameAndLanguageId)
            return new ApiResponse("This is such name and language", false);

        boolean existsByTextAndLanguageId = taskRepository
                .existsByTextAndLanguageId(taskDto.getText(), taskDto.getLanguageId());
        if (existsByTextAndLanguageId)
            return new ApiResponse("This is such text and language", false);

        Optional<Language> languageById = languageRepository.findById(taskDto.getLanguageId());
        if (!languageById.isPresent())
            return new ApiResponse("language not found", false);

        Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setHelp(taskDto.getHelp());
        task.setLanguage(languageById.get());
        taskRepository.save(task);
        return new ApiResponse("task edited",true);
    }

}
