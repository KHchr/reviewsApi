package reviews.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reviews.PageDecorator;
import reviews.model.Review;
import reviews.repository.ReviewsRepository;

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
    public PageDecorator<Review> getPageDishReviews(Long id, Pageable pageable) {
        log.info("IN ReviewsServiceImpl getPageDishReviews");
        return new PageDecorator<>(reviewsRepository.findByDishId(id, pageable));
    }

    @Override
    public Review getById(Long id) {
        return reviewsRepository.getOne(id);
    }

}
