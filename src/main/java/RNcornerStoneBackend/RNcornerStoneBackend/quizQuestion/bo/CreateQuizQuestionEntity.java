package RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.bo;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.enums.Levels;
import jakarta.validation.constraints.NotNull;

public class CreateQuizQuestionEntity {

    @NotNull(message = "The 'questionText' field is required and it's missing")
    private String questionText;

    @NotNull(message = "The 'option_a' field is required and it's missing")
    private String option_a;

    @NotNull(message = "The 'option_b' field is required and it's missing")
    private String option_b;

    @NotNull(message = "The 'option_c' field is required and it's missing")
    private String option_c;

    @NotNull(message = "The 'option_d' field is required and it's missing")
    private String option_d;


    @NotNull(message = "The 'correctOption' field is required and it's missing")
    private String correctOption;

    @NotNull(message = "The 'level' field is required and it's missing")
    private Levels level;

    @NotNull(message = "The 'rewardAmount' field is required and it's missing")
    private Double rewardAmount;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOption_a() {
        return option_a;
    }

    public void setOption_a(String option_a) {
        this.option_a = option_a;
    }

    public String getOption_b() {
        return option_b;
    }

    public void setOption_b(String option_b) {
        this.option_b = option_b;
    }

    public String getOption_c() {
        return option_c;
    }

    public void setOption_c(String option_c) {
        this.option_c = option_c;
    }

    public String getOption_d() {
        return option_d;
    }

    public void setOption_d(String option_d) {
        this.option_d = option_d;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public Levels getLevel() {
        return level;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }
}
