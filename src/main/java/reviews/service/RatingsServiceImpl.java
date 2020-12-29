package reviews.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reviews.model.Rating;
import reviews.repository.RatingsRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RatingsServiceImpl implements RatingsService{

    RatingsRepository ratingsRepository;

    public RatingsServiceImpl(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    @Override
    public void save(Rating rating) {
        log.info("IN RatingsServiceImpl save");
        ratingsRepository.save(rating);
    }

    @Override
    public List<Rating> getListByIdIn(List<Long> ids) {
        log.info("IN RatingsServiceImpl getListByIdIn {}", ids);
        return new ArrayList<>(ratingsRepository.findByIdIn(ids));
    }

    @Override
    public Rating getById(Long id) {
        log.info("IN RatingsServiceImpl getById {}", id);
        return ratingsRepository.getOne(id);
    }
}
