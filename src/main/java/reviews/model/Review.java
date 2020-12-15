package reviews.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "reviews")
@Data
public class Review extends BaseEntity {

    @Column(name = "dish_id")
    private Long dishId;

    @Column(name = "author_nickname")
    private String authorNickname;

    @Column
    private Integer grade;

    @Column
    private String text;

    @Column
    @JsonFormat(pattern="dd.MM.yyyy", timezone="Europe/Moscow")
    private Date date;
}
