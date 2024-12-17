package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.bo;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


import java.util.Date;
import java.util.Objects;

public class CreateAttemptEntity {

    @NotNull(message = "The 'childUserEntity' field is required and it's missing")
    private UserEntity childUserEntity;

    @NotNull(message = "The 'quizQuestionEntity' field is required and it's missing")
    private QuizQuestionEntity quizQuestionEntity;

    @NotNull(message = "The 'answer' field is required and it's missing")
    private Boolean answer;

    @NotNull(message = "The 'date' field is required and it's missing")
    @PastOrPresent(message = "The 'date' must be in the present or past")
    private Date date;


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

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAttemptEntity that = (CreateAttemptEntity) o;
        return Objects.equals(childUserEntity, that.childUserEntity) &&
                Objects.equals(quizQuestionEntity, that.quizQuestionEntity) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(childUserEntity, quizQuestionEntity, answer, date);
    }
}
