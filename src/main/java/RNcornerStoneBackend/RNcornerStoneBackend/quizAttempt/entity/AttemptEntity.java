package RNcornerStoneBackend.RNcornerStoneBackend.quizAttempt.entity;

import RNcornerStoneBackend.RNcornerStoneBackend.quizQuestion.entity.QuizQuestionEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AttemptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "child_user_id")
    private UserEntity childUserEntity;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuizQuestionEntity quizQuestionEntity;

    private Boolean answer;

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


}
