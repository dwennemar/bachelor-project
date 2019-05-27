package de.dwennemar.bachelor.api;

import de.dwennemar.bachelor.dto.Key;
import de.dwennemar.bachelor.dto.Review;
import de.dwennemar.bachelor.services.KeyService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/review")
public class ReviewEndpoint implements EndpointConstants {
    @GetMapping("/product/{productId}")
    public Review[] getReviewByProduct(@PathVariable Long productId) {
        return getRest().getForObject(DATA_URL+REVIEW+PRODUCT+"/"+productId, Review[].class);
    }

    @GetMapping("/user/{userId}")
    public Review[] getReviewByUser(@PathVariable Long userId) {
        return getRest().getForObject(DATA_URL+REVIEW+USER+"/"+userId, Review[].class);
    }

    @PostMapping
    public Review newReview(@RequestBody Review review) {
        getRest().postForObject(KEY_URL+KEY, KeyService.newKey(KeyService.KeyScope.MARKETING,
                review.getAuthor().getId()), Key.class);

        return getRest().postForObject(DATA_URL+REVIEW, review, Review.class);
    }

    @PutMapping("/{id}")
    public void updateReview(@RequestBody Review pReview, @PathVariable Long id) {
        getRest().put(DATA_URL+REVIEW+"/"+id, pReview);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        getRest().delete(DATA_URL+REVIEW+"/"+id);
    }

    private RestTemplate getRest() {
        return new RestTemplate();
    }

}
