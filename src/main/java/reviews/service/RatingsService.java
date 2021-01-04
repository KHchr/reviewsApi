package reviews.service;

import reviews.model.Rating;
import reviews.model.Review;

import java.util.List;

public interface RatingsService {

    void saveRatingByReviews(List<Review> reviews);

    List<Rating> getListByIdIn(List<Long> ids);
    Rating getById(Long id);
}
