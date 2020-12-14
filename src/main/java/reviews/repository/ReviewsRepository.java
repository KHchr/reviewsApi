package reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reviews.model.Review;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, Long> {

    List<Review> findByDishId(Long dishId);

}
