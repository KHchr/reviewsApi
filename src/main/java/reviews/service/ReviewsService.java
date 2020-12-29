package reviews.service;

import org.springframework.data.domain.Pageable;
import reviews.PageDecorator;
import reviews.model.Review;

import java.util.List;


public interface ReviewsService {

    void save(Review review);

    PageDecorator<Review> getPageDishReviews(Long id, Pageable pageable);

    List<Review> getListDishReviews(Long id);

    Review getById(Long id);
}
