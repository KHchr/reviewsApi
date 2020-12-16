package reviews.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reviews.PageDecorator;
import reviews.model.Review;
import reviews.service.ReviewsService;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsRestControllerV1 {

    private final ReviewsService reviewsService;

    public ReviewsRestControllerV1(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

//    @RequestMapping(value = "{dishId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<List<Review>> getAllReviews(@PathVariable ("dishId") Long dishId){
//
//        List<Review> reviews = this.reviewsService.getAllDishReviews(dishId);
//
//        return new ResponseEntity<>(reviews, HttpStatus.OK);
//    }

    @RequestMapping(value = "{dishId}", method = RequestMethod.GET)
    public ResponseEntity<PageDecorator<Review>> getAllReviews(@PathVariable ("dishId") Long dishId,
                                                      @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                      @RequestParam(required = false, defaultValue = "0") Integer page){


        Pageable pageable = PageRequest.of(page, limit, Sort.by("date").descending());

        PageDecorator<Review> reviews = this.reviewsService.getPageDishReviews(dishId, pageable);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }



    @RequestMapping(value = "/saveReview", method = RequestMethod.POST)
    public ResponseEntity<Review> saveReview(@RequestBody Review review){
        HttpHeaders headers = new HttpHeaders();
        if (review == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Date date = new Date();
        review.setDate(date);
        this.reviewsService.save(review);
        return new ResponseEntity<>(review, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{reviewId}/like", method = RequestMethod.PATCH)
    public ResponseEntity<Review> addLike(@PathVariable("reviewId") Long reviewId){
        if (reviewId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Review review = this.reviewsService.getById(reviewId);

        review.setLikes(review.getLikes() + 1);

        reviewsService.save(review);

        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @RequestMapping(value = "/{reviewId}/dislike", method = RequestMethod.PATCH)
    public ResponseEntity<Review> addDislike(@PathVariable("reviewId") Long reviewId){
        if (reviewId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Review review = this.reviewsService.getById(reviewId);

        review.setLikes(review.getDislikes() + 1);

        reviewsService.save(review);

        return new ResponseEntity<>(review, HttpStatus.OK);
    }


}
