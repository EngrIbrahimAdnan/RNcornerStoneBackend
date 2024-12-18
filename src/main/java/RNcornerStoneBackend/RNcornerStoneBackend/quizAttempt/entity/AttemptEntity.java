package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AttemptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "child_user_id")
    private UserEntity childUserEntity;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuizQuestionEntity quizQuestionEntity;

    private String selectedOption;

    private boolean isCorrect;

    private double rewardEarned;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getChildUserEntity() {
        return childUserEntity;
    }

    public void setChildUserEntity(UserEntity childUserEntity) {
        this.childUserEntity = childUserEntity;
    }

    public QuizQuestionEntity getQuizQuestionEntity() {
        return quizQuestionEntity;
    }

    public void setQuizQuestionEntity(QuizQuestionEntity quizQuestionEntity) {
        this.quizQuestionEntity = quizQuestionEntity;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public double getRewardEarned() {
        return rewardEarned;
    }

    public void setRewardEarned(double rewardEarned) {
        this.rewardEarned = rewardEarned;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
