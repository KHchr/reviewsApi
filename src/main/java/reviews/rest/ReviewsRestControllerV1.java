package reviews.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reviews.PageDecorator;
import reviews.model.Review;
import reviews.service.RatingsService;
import reviews.service.ReviewsService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@SessionAttributes({"like", "dislike"})
@RequestMapping(value = "/api/v1/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewsRestControllerV1 {

    private final ReviewsService reviewsService;
    private final RatingsService ratingsService;

    public ReviewsRestControllerV1(ReviewsService reviewsService, RatingsService ratingsService) {
        this.reviewsService = reviewsService;
        this.ratingsService = ratingsService;
    }

    @GetMapping("/{dishId}")
    public ResponseEntity<PageDecorator<Review>> getAllReviews(@PathVariable ("dishId") Long dishId,
                                                      @RequestParam(required = false, defaultValue = "10") int limit,
                                                      @RequestParam(required = false, defaultValue = "0") int page){
        if(dishId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reviewsService.getPageDishReviews(dishId, page, limit), HttpStatus.OK);
    }



    @PostMapping("/saveReview")
    public ResponseEntity<Object> saveReview(@RequestBody Review review){
        HttpHeaders headers = new HttpHeaders();
        if (review == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Review> reviews = reviewsService.getListDishReviews(review.getDishId());

        ratingsService.saveRatingByReviews(reviews);

        reviewsService.save(review);
        return new ResponseEntity<>(review, headers, HttpStatus.CREATED);
    }

    @PatchMapping("/{reviewId}/like")
    public ResponseEntity<Review> addLike(@PathVariable("reviewId") Long reviewId, HttpSession session){
        if (reviewId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Review review = reviewsService.getById(reviewId);

        reviewsService.likeReview(review, session);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PatchMapping("/{reviewId}/dislike")
    public ResponseEntity<Review> addDislike(@PathVariable("reviewId") Long reviewId, HttpSession session){
        if (reviewId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Review review = reviewsService.getById(reviewId);

        reviewsService.dislikeReview(review, session);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }


}
