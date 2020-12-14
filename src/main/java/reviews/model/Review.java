package reviews.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "reviews")
@Data
public class Review extends BaseEntity {

    @Column(name = "dish_id")
    private Long dishId;

    @Column(name = "author_nickname")
    private String authorNickname;

    @Column(name = "review")
    private String review;
}
