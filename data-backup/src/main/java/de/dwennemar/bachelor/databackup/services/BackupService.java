package de.dwennemar.bachelor.databackup.services;

import de.dwennemar.bachelor.databackup.persist.ProductRepository;
import de.dwennemar.bachelor.databackup.persist.ReviewRepository;
import de.dwennemar.bachelor.databackup.persist.UserAddressRepository;
import de.dwennemar.bachelor.databackup.persist.UserRepository;
import de.dwennemar.bachelor.databackup.persist.impl.Review;
import de.dwennemar.bachelor.databackup.persist.impl.User;
import de.dwennemar.bachelor.databackup.persist.impl.UserAddress;
import de.dwennemar.bachelor.databackup.exception.CryptoServiceFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackupService {
    private final Logger log = LoggerFactory.getLogger(BackupService.class);

    //Other services
    private final ApiService apiService;
    private final CryptoService cryptoService;

    //JPA repositories
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserAddressRepository userAddressRepository;
    private final ReviewRepository reviewRepository;


    @Autowired
    public BackupService(UserRepository userRepository, ApiService apiService, CryptoService cryptoService,
        ProductRepository productRepository, UserAddressRepository userAddressRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.apiService = apiService;
        this.cryptoService = cryptoService;
        this.productRepository = productRepository;
        this.userAddressRepository = userAddressRepository;
        this.reviewRepository = reviewRepository;
    }

    public void backupAll() {
        this.userRepository.saveAll(encryptUser());
        this.productRepository.saveAll(this.apiService.getProduct());
        this.reviewRepository.saveAll(encryptReview());
        this.userAddressRepository.saveAll(encryptAddress());
    }

    public List<User> encryptUser () {
        List<User> user = this.apiService.getUser();
        List<User> backupUser = new ArrayList<>(user.size());
        user.forEach(u -> {
            String key = this.apiService.getKey(u.getId().toString(), "3").get(0).getKey();
            try {
                User tmp = new User();
                tmp.setId(u.getId());
                tmp.setUsername(this.cryptoService.encrypt(u.getUsername(), key));
                tmp.setPassword(this.cryptoService.encrypt(u.getPassword(), key));

                backupUser.add(tmp);
            } catch (CryptoServiceFailedException e) {
                log.error(e.toString());
            }
        });
        log.info("Successfully encrypted User!");
        return backupUser;
    }

    public List<Review> encryptReview() {
        List<Review> reviews = this.apiService.getReview();
        List<Review> backupReviews = new ArrayList<>(reviews.size());

        reviews.forEach(r -> {
            String key = this.apiService.getKey(r.getAuthor().getId().toString(), "2").get(0).getKey();
            try {
                Review tmp = new Review();

                tmp.setId(r.getId());
                tmp.setAuthor(r.getAuthor());
                tmp.setContent(this.cryptoService.encrypt(r.getContent(), key));
                tmp.setProduct(r.getProduct());
                tmp.setTitle(this.cryptoService.encrypt(r.getTitle(), key));

                backupReviews.add(tmp);
            } catch (CryptoServiceFailedException e) {
                log.error(e.toString());
            }
        });
        log.info("Reviews successfully encrypted!");
        return backupReviews;
    }

    public List<UserAddress> encryptAddress() {
        List<UserAddress> userAddresses = this.apiService.getAddress();
        List<UserAddress> backupAddresses = new ArrayList<>(userAddresses.size());

        userAddresses.forEach(a -> {
            String key = this.apiService.getKey(a.getUser().getId().toString(), "1").get(0).getKey();
            try {
                UserAddress tmp = new UserAddress();

                tmp.setId(a.getId());
                tmp.setAddress(this.cryptoService.encrypt(a.getAddress(), key));
                tmp.setCity(this.cryptoService.encrypt(a.getCity(), key));
                tmp.setCountry(this.cryptoService.encrypt(a.getCountry(), key));
                tmp.setFirstName(this.cryptoService.encrypt(a.getFirstName(), key));
                tmp.setLastName(this.cryptoService.encrypt(a.getLastName(), key));
                tmp.setPostcode(this.cryptoService.encrypt(a.getPostcode(), key));
                tmp.setUser(a.getUser());

                backupAddresses.add(tmp);
            } catch (CryptoServiceFailedException e) {
                log.error(e.toString());
            }
        });

        log.info("Successfully encrypted addresses!");
        return backupAddresses;
    }
}
