package reviews.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reviews.PageDecorator;
import reviews.model.Review;
import reviews.service.ReviewsService;

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
                                                      @RequestParam(required = false, defaultValue = "7") Integer limit,
                                                      @RequestParam(required = false, defaultValue = "1") Integer page){


        Pageable pageable = PageRequest.of(page, limit);

        PageDecorator<Review> reviews = this.reviewsService.getPageDishReviews(dishId, pageable);


        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }



    @RequestMapping(value = "/saveReview", method = RequestMethod.POST)
    public ResponseEntity<Review> saveReview(@RequestBody Review review){
        HttpHeaders headers = new HttpHeaders();

        if (review == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.reviewsService.save(review);

        return new ResponseEntity<>(review, headers, HttpStatus.CREATED);
    }


}
