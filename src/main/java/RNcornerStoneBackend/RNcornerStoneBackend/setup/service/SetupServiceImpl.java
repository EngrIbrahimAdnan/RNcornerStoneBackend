package RNcornerStoneBackend.RNcornerStoneBackend.setup.service;

import RNcornerStoneBackend.RNcornerStoneBackend.setup.data.DataLoader;
import RNcornerStoneBackend.RNcornerStoneBackend.setup.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.setup.repository.QuizQuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetupServiceImpl implements SetupService {

    private final QuizQuestionsRepository quizQuestionsRepository;

    public SetupServiceImpl(QuizQuestionsRepository quizQuestionsRepository) {
        this.quizQuestionsRepository = quizQuestionsRepository;
    }


    @Override
    public String addQuestionsToDatabaseFromFile(String file) {

        // returns a list of all questions defined from "file"
        // returns Null if the file is not found
        List<QuizQuestionEntity> quizQuestions = DataLoader.loadQuizQuestions(file);

        // Returns here only when the file is not found
        if (quizQuestions==null){
            return "Unable to find the file. Check the location of the file '"+file+"'";
        }

        try {
            // Loop through and save each question
            for (QuizQuestionEntity question : quizQuestions) {
                quizQuestionsRepository.save(question);
            }
            return null;

        } catch (Exception e) {
            // returns the following string if there is any issue when saving to the database
            return "Unable to add questions to database. Ensure they are the expected json structure";
        }
    }
}