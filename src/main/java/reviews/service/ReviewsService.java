package reviews.service;

import reviews.model.Review;

import java.util.List;

public interface ReviewsService {

    void save(Review review);

    List<Review> getAllDishReviews(Long id);
}
