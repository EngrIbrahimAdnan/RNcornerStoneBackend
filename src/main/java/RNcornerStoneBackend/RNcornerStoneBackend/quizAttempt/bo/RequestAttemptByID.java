package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo;

import jakarta.validation.constraints.NotNull;

public class RequestAttemptByID {
    @NotNull(message = "The 'question_id' field is required and it's missing")
    private Long question_id;

    public @NotNull(message = "The 'question_id' field is required and it's missing") Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(@NotNull(message = "The 'question_id' field is required and it's missing") Long question_id) {
        this.question_id = question_id;
    }
}
