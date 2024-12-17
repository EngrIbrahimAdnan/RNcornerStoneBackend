package RNcornerStoneBackend.RNcornerStoneBackend.Chore.Entity;

import RNcornerStoneBackend.RNcornerStoneBackend.child.entity.ChildEntity;
import RNcornerStoneBackend.RNcornerStoneBackend.user.entity.UserEntity;
import jakarta.persistence.*;

import java.util.Date;


@Entity
public class ChoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private UserEntity parent;

    @Column(nullable = true)
    private Double rewardsAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    private ChildEntity child;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    public ChoreEntity() {
        this.id = id;
        this.parent = parent;
        this.rewardsAmount = rewardsAmount;
        this.status = status;
        this.child = child;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getParent() {
        return parent;
    }

    public void setParent(UserEntity parent) {
        this.parent = parent;
    }

    public Double getRewardsAmount() {
        return rewardsAmount;
    }

    public void setRewardsAmount(Double rewardsAmount) {
        this.rewardsAmount = rewardsAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ChildEntity getChild() {
        return child;
    }

    public void setChild(ChildEntity child) {
        this.child = child;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
