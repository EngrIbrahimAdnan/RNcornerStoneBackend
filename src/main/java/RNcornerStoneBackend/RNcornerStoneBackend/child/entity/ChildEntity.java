package RNcornerStoneBackend.RNcornerStoneBackend.child.entity;

import RNcornerStoneBackend.RNcornerStoneBackend.User.entity.UserEntity;
import jakarta.persistence.*;

import java.util.Date;


@Entity
public class ChildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private UserEntity parent;

    @Column(nullable = true)
    private Double Balance;

    @Column(nullable = false)
    private Date DateOfBirth;

    public ChildEntity() {
        this.id = id;
        this.user = user;
        this.parent = parent;
        this.Balance = Balance;
        this.DateOfBirth = DateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getParent() {
        return parent;
    }

    public void setParent(UserEntity parent) {
        this.parent = parent;
    }

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }
}
