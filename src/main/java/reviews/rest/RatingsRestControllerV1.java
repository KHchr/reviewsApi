package reviews.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reviews.model.Rating;
import reviews.service.RatingsService;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1/rating", produces = MediaType.APPLICATION_JSON_VALUE)
public class RatingsRestControllerV1 {

    private final RatingsService ratingsService;

    public RatingsRestControllerV1(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Rating>> getRatingsByIdIn(@RequestParam(value = "id", required = false) List<Long> dishIds){
        if(dishIds.size() == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ratingsService.getListByIdIn(dishIds), HttpStatus.OK);
    }
}
