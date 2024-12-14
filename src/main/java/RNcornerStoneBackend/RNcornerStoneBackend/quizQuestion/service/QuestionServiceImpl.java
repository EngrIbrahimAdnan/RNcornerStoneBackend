package RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo.CreateQuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.repository.QuizQuestionsRepository;
import RNcornerStoneBackend.RNcornerStoneBackend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{
    private final QuizQuestionsRepository quizQuestionsRepository;

    public QuestionServiceImpl(QuizQuestionsRepository quizQuestionsRepository) {
        this.quizQuestionsRepository = quizQuestionsRepository;
    }

    @Override
    public Optional<QuizQuestionEntity> getQuestionById(Long id){
        return quizQuestionsRepository.findById(id);
    }

    @Override
    public void addQuestion(CreateQuizQuestionEntity request){

        QuizQuestionEntity quizQuestion = new QuizQuestionEntity();

        quizQuestion.setQuestionText(request.getQuestionText());
        quizQuestion.setOption_a(request.getOption_a());
        quizQuestion.setOption_b(request.getOption_b());
        quizQuestion.setOption_c(request.getOption_c());
        quizQuestion.setOption_d(request.getOption_d());
        quizQuestion.setCorrectOption(request.getCorrectOption());
        quizQuestion.setLevel(request.getLevel());
        quizQuestion.setRewardAmount(request.getRewardAmount());

        try {
            quizQuestionsRepository.save(quizQuestion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
