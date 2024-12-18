package RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.service;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo.CreateQuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo.RequestQuestionById;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.repository.QuizQuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizQuestionServiceImpl implements QuizQuestionService {
    private final QuizQuestionsRepository quizQuestionsRepository;

    public QuizQuestionServiceImpl(QuizQuestionsRepository quizQuestionsRepository) {
        this.quizQuestionsRepository = quizQuestionsRepository;
    }

    @Override
    public Optional<QuizQuestionEntity> getQuestionById(Long questionId){
        return quizQuestionsRepository.findById(questionId);
    }

    @Override
    public List<QuizQuestionEntity> getAll(){
        return quizQuestionsRepository.findAll();
    }

    @Override
    public String addQuestion(CreateQuizQuestionEntity request){


        // do checkups if necessary here before assigning saving request to repository
        // e.g. identical questions, expected length of certain fields
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
            return "Error adding '"+quizQuestion.getQuestionText()+"' question: "+e;
        }
        return null;


    }

}
