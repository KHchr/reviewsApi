package reviews.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reviews.PageDecorator;
import reviews.model.Review;
import reviews.repository.ReviewsRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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
        Date date = new Date();
        review.setDate(date);
        review.setLikes(0);
        review.setDislikes(0);
        reviewsRepository.save(review);
    }

    @Override
    public void likeReview(Review review, HttpSession session) {
        log.info("IN ReviewsServiceImpl updateReview {}", review);
        //first time like review
        if (session.getAttribute(review.getId().toString()) == null){
            session.setAttribute(review.getId().toString(), "");
            return;
        }
        //unlike review
        if (session.getAttribute(review.getId().toString()).equals("like") && review.getLikes() > 0){
            session.setAttribute(review.getId().toString(), "");
            review.setLikes(review.getLikes() - 1);
            reviewsRepository.save(review);
            return;
        }
        //like disliked review
        if (session.getAttribute(review.getId().toString()).equals("dislike") && review.getDislikes() > 0){
            session.setAttribute(review.getId().toString(), "like");
            review.setLikes(review.getLikes() + 1);
            review.setDislikes(review.getDislikes() - 1);
            reviewsRepository.save(review);
            return;
        }
        //usual like
        session.setAttribute(review.getId().toString(), "like");
        review.setLikes(review.getLikes() + 1);
        reviewsRepository.save(review);
    }

    @Override
    public void dislikeReview(Review review, HttpSession session) {
        //first time dislike review
        if (session.getAttribute(review.getId().toString()) == null){
            session.setAttribute(review.getId().toString(), "");
            return;
        }
        //remove dislike review
        if (session.getAttribute(review.getId().toString()).equals("dislike") && review.getDislikes() > 0){
            session.setAttribute(review.getId().toString(), "");
            review.setDislikes(review.getDislikes() - 1);
            reviewsRepository.save(review);
            return;
        }
        //dislike liked review
        if (session.getAttribute(review.getId().toString()).equals("like") && review.getLikes() > 0){
            session.setAttribute(review.getId().toString(), "dislike");
            review.setLikes(review.getLikes() - 1);
            review.setDislikes(review.getDislikes() + 1);
            reviewsRepository.save(review);
            return;
        }
        //usual dislike
        session.setAttribute(review.getId().toString(), "dislike");
        review.setDislikes(review.getDislikes() + 1);
        reviewsRepository.save(review);
    }


    @Override
    public PageDecorator<Review> getPageDishReviews(Long id, int page, int limit) {
        log.info("IN ReviewsServiceImpl getPageDishReviews");
        Pageable pageable = PageRequest.of(page, limit, Sort.by("date").descending());
        return new PageDecorator<>(reviewsRepository.findByDishId(id, pageable));
    }

    @Override
    public List<Review> getListDishReviews(Long id) {
        log.info("IN ReviewsServiceImpl getListDishReviews {}", id);
        return new ArrayList<>(reviewsRepository.findByDishId(id));
    }

    @Override
    public Review getById(Long id) {
        log.info("IN ReviewsServiceImpl getById {}", id);
        return reviewsRepository.getOne(id);
    }

}
