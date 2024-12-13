package RNcornerStoneBackend.RNcornerStoneBackend.Setup.service;

import RNcornerStoneBackend.RNcornerStoneBackend.Setup.data.DataLoader;
import RNcornerStoneBackend.RNcornerStoneBackend.Setup.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.Setup.repository.QuizQuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetupServiceImpl implements SetupService{

    private final QuizQuestionsRepository quizQuestionsRepository;

    public SetupServiceImpl(QuizQuestionsRepository quizQuestionsRepository) {
        this.quizQuestionsRepository = quizQuestionsRepository;
    }

    @Override
    public String addQuizQuestionsToRepository(){

        List<QuizQuestionEntity> quizQuestions = DataLoader.loadQuizQuestions();

        // Loop through and save each question
        for (QuizQuestionEntity question : quizQuestions) {
            quizQuestionsRepository.save(question);
        }
        return null;
    }


}