package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo;

import jakarta.validation.constraints.NotNull;

public class CreateAttemptEntity {

    @NotNull(message = "The 'question_id' field is required and it's missing")
    private Long question_id;

    @NotNull(message = "The 'answer' field is required and it's missing")
    private Boolean answer;

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}
