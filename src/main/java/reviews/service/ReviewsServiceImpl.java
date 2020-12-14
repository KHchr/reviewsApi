package reviews.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reviews.PageDecorator;
import reviews.model.Review;
import reviews.repository.ReviewsRepository;

import java.util.List;

@Slf4j
@Service
public class ReviewsServiceImpl implements ReviewsService{


    ReviewsRepository reviewsRepository;

    public ReviewsServiceImpl(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public void save(Review review) {
        log.info("IN ReviewsServiceImpl save {}", review);
        reviewsRepository.save(review);
    }

    @Override
    public List<Review> getAllDishReviews(Long dishId) {
        log.info("IN ReviewsServiceImpl getAll");
        return reviewsRepository.findByDishId(dishId);
    }

    @Override
    public PageDecorator<Review> getPageDishReviews(Long id, Pageable pageable) {

        return new PageDecorator<>(reviewsRepository.findByDishId(id, pageable));
    }

    public PageDecorator<Review> getData(Long dishId, Pageable pageable) {
        return new PageDecorator<>(reviewsRepository.findByDishId(dishId, pageable));
    }
}
