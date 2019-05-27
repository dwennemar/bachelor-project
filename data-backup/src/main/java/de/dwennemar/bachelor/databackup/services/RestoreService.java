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
public class RestoreService {
    private final Logger log = LoggerFactory.getLogger(RestoreService.class);

    //Other services
    private final ApiService apiService;
    private final CryptoService cryptoService;

    //JPA repositories
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserAddressRepository userAddressRepository;
    private final ReviewRepository reviewRepository;


    @Autowired
    public RestoreService(UserRepository userRepository, ApiService apiService, CryptoService cryptoService,
                         ProductRepository productRepository, UserAddressRepository userAddressRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.apiService = apiService;
        this.cryptoService = cryptoService;
        this.productRepository = productRepository;
        this.userAddressRepository = userAddressRepository;
        this.reviewRepository = reviewRepository;
    }

    public void backupAll() {
        this.userRepository.saveAll(decryptUser());
        this.productRepository.saveAll(this.productRepository.findAll());
        this.reviewRepository.saveAll(decryptReview());
        this.userAddressRepository.saveAll(decryptAddress());
    }

    public List<User> decryptUser () {
        List<User> user = (List<User>) this.userRepository.findAll();
        List<User> restoreUser = new ArrayList<>(user.size());
        user.forEach(u -> {
            String key = this.apiService.getKey(u.getId().toString(), "3").get(0).getKey();
            try {
                User tmp = new User();
                tmp.setId(u.getId());
                tmp.setUsername(this.cryptoService.decrypt(u.getUsername(), key));
                tmp.setPassword(this.cryptoService.decrypt(u.getPassword(), key));

                restoreUser.add(tmp);
            } catch (CryptoServiceFailedException e) {
                log.error(e.toString());
            }
        });
        log.info("Successfully decrypted User!");
        return restoreUser;
    }

    public List<Review> decryptReview() {
        List<Review> reviews = (List<Review>) this.reviewRepository.findAll();
        List<Review> restoreReview = new ArrayList<>(reviews.size());

        reviews.forEach(r -> {
            String key = this.apiService.getKey(r.getAuthor().getId().toString(), "2").get(0).getKey();
            try {
                Review tmp = new Review();

                tmp.setId(r.getId());
                tmp.setAuthor(r.getAuthor());
                tmp.setContent(this.cryptoService.decrypt(r.getContent(), key));
                tmp.setProduct(r.getProduct());
                tmp.setTitle(this.cryptoService.decrypt(r.getTitle(), key));

                restoreReview.add(tmp);
            } catch (CryptoServiceFailedException e) {
                log.error(e.toString());
            }
        });
        log.info("Reviews successfully decrypted!");
        return restoreReview;
    }

    public List<UserAddress> decryptAddress() {
        List<UserAddress> userAddresses = (List<UserAddress>) this.userAddressRepository.findAll();
        List<UserAddress> restoreAddresses = new ArrayList<>(userAddresses.size());

        userAddresses.forEach(a -> {
            String key = this.apiService.getKey(a.getUser().getId().toString(), "1").get(0).getKey();
            try {
                UserAddress tmp = new UserAddress();

                tmp.setId(a.getId());
                tmp.setAddress(this.cryptoService.decrypt(a.getAddress(), key));
                tmp.setCity(this.cryptoService.decrypt(a.getCity(), key));
                tmp.setCountry(this.cryptoService.decrypt(a.getCountry(), key));
                tmp.setFirstName(this.cryptoService.decrypt(a.getFirstName(), key));
                tmp.setLastName(this.cryptoService.decrypt(a.getLastName(), key));
                tmp.setPostcode(this.cryptoService.decrypt(a.getPostcode(), key));
                tmp.setUser(a.getUser());

                restoreAddresses.add(tmp);
            } catch (CryptoServiceFailedException e) {
                log.error(e.toString());
            }
        });

        log.info("Successfully decrypted addresses!");
        return restoreAddresses;
    }

}

