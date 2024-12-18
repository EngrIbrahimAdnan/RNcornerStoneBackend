package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateAttemptEntity {

    @NotNull(message = "The 'question_id' field is required and it's missing")
    private Long question_id;

    @NotNull(message = "The 'selectedOption' field is required and it's missing")
    @Pattern(regexp = "^[a-dA-D]$", message = "The selectedOption must be 'a', 'b', 'c', or 'd'")
    private String selectedOption;

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getSelectedOption() {
        return selectedOption != null ? selectedOption.toLowerCase() : null;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption != null ? selectedOption.toLowerCase() : null;
    }
}
