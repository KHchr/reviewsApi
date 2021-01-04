package reviews.service;

import reviews.PageDecorator;
import reviews.model.Review;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface ReviewsService {

    void save(Review review);

    void likeReview(Review review, HttpSession session);
    void dislikeReview(Review review, HttpSession session);
    PageDecorator<Review> getPageDishReviews(Long id, int page, int limit);

    List<Review> getListDishReviews(Long id);

    Review getById(Long id);
}
