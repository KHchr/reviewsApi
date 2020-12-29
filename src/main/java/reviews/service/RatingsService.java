package reviews.service;

import reviews.model.Rating;

import java.util.List;

public interface RatingsService {

    void save(Rating rating);

    List<Rating> getListByIdIn(List<Long> ids);
    Rating getById(Long id);
}
