package RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo;

import jakarta.validation.constraints.NotNull;

public class RequestQuestionById {
    @NotNull(message = "The 'question_id' field is required and it's missing")
    private Long question_id;

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }
}
