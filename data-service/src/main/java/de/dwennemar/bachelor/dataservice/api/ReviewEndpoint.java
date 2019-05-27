package de.dwennemar.bachelor.dataservice.api;

import de.dwennemar.bachelor.dataservice.persist.marketing.ReviewRepository;
import de.dwennemar.bachelor.dataservice.persist.marketing.impl.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewEndpoint {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewEndpoint(ReviewRepository pRepository) {
        this.reviewRepository = pRepository;
    }

    @GetMapping("/product/{productId}")
    public Iterable<Review> getReviewByProduct(@PathVariable Long productId) {
        return this.reviewRepository.getByProductId(productId);
    }

    @GetMapping("/user/{userId}")
    public Iterable<Review> getReviewByUser(@PathVariable Long userId) {
        return this.reviewRepository.getByAuthorId(userId);
    }

    @PostMapping
    public Review newReview(@RequestBody Review review) {
        return this.reviewRepository.save(review);
    }

    @PutMapping("/{id}")
    public Optional<Review> updateReview(@RequestBody Review pReview, @PathVariable Long id) {
        return this.reviewRepository.findById(id).map(review -> {
            review.setAuthor(pReview.getAuthor());
            review.setContent(pReview.getContent());
            review.setProduct(pReview.getProduct());
            review.setTitle(pReview.getTitle());
            return this.reviewRepository.save(review);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        this.reviewRepository.deleteById(id);
    }

}
