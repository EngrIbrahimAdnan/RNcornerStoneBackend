package RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.enums.Levels;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class QuizQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;
    private String option_a;
    private String option_b;
    private String option_c;
    private String option_d;

    private String correctOption;

    private Levels level;

    private Double rewardAmount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizQuestionEntity that = (QuizQuestionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(questionText, that.questionText) && Objects.equals(option_a, that.option_a) && Objects.equals(option_b, that.option_b) && Objects.equals(option_c, that.option_c) && Objects.equals(option_d, that.option_d) && Objects.equals(correctOption, that.correctOption) && Objects.equals(level, that.level) && Objects.equals(rewardAmount, that.rewardAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, option_a, option_b, option_c, option_d, correctOption, level, rewardAmount);
    }

}
