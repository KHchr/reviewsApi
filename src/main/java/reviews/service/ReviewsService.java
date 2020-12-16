package reviews.service;

import org.springframework.data.domain.Pageable;
import reviews.PageDecorator;
import reviews.model.Review;


public interface ReviewsService {

    void save(Review review);

    PageDecorator<Review> getPageDishReviews(Long id, Pageable pageable);

    Review getById(Long id);
}
