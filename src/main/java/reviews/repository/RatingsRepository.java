package reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reviews.model.Rating;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByIdIn(List<Long> ids);
}
