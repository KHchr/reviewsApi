package reviews.service;

import org.springframework.data.domain.Pageable;
import reviews.PageDecorator;
import reviews.model.Review;

import java.util.List;

public interface ReviewsService {

    void save(Review review);

    List<Review> getAllDishReviews(Long id);

    PageDecorator<Review> getPageDishReviews(Long id, Pageable pageable);
}
