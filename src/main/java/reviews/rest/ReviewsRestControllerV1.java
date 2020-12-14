package reviews.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reviews.model.Review;
import reviews.service.ReviewsService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsRestControllerV1 {

    private final ReviewsService reviewsService;

    public ReviewsRestControllerV1(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @RequestMapping(value = "{dishId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable ("dishId") Long dishId){

        List<Review> reviews = this.reviewsService.getAllDishReviews(dishId);

        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }



    @RequestMapping(value = "/saveReview", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Review> saveReview(@RequestBody @Valid Review review){
        HttpHeaders headers = new HttpHeaders();

        if (review == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.reviewsService.save(review);

        return new ResponseEntity<>(review, headers, HttpStatus.CREATED);
    }
}
