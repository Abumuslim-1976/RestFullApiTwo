package uz.pdp.RestFullApiTwo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.RestFullApiTwo.Entity.Answer;
import uz.pdp.RestFullApiTwo.Entity.Task;
import uz.pdp.RestFullApiTwo.Entity.User;
import uz.pdp.RestFullApiTwo.payload.AnswerDto;
import uz.pdp.RestFullApiTwo.payload.ApiResponse;
import uz.pdp.RestFullApiTwo.repository.AnswerRepository;
import uz.pdp.RestFullApiTwo.repository.TaskRepository;
import uz.pdp.RestFullApiTwo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;


    public ApiResponse createAnswer(AnswerDto answerDto) {
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found", false);

        Answer answer = new Answer();
        answer.setText(answerDto.getText());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("answer created", true);
    }


    public List<Answer> getAllAnswer() {
        return answerRepository.findAll();
    }


    public Answer getAnswer(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElseGet(Answer::new);
    }


    public ApiResponse deleteAnswer(Integer id) {
        try {
            answerRepository.deleteById(id);
        } catch (Exception e) {
            return new ApiResponse("answer not found", false);
        }
        return new ApiResponse("answer deleted", true);
    }


    public ApiResponse editAnswer(Integer id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ApiResponse("answer not found", false);

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("task not found", false);

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found", false);

        Answer answer = optionalAnswer.get();
        answer.setText(answerDto.getText());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("answer edited", true);
    }

}
