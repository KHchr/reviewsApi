package reviews.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "ratings")
@Data
public class Rating {

    @Id
    private Long id;

    @Column(name = "rating")
    private Double rating;

}
