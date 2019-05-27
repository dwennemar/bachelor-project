package de.dwennemar.bachelor.dataservice.api;

import de.dwennemar.bachelor.dataservice.persist.juristic.UserAddressRepository;
import de.dwennemar.bachelor.dataservice.persist.juristic.impl.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("address")
public class AddressEndpoint {
    private final UserAddressRepository userAddressRepository;

    @Autowired
    public AddressEndpoint(UserAddressRepository pRepository) {
        this.userAddressRepository = pRepository;
    }

    @GetMapping("/all")
    public Iterable<UserAddress> getAllUsers() {
        return this.userAddressRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserAddress> getUser(@PathVariable Long id) {
        return userAddressRepository.findById(id);
    }

    @PostMapping
    public UserAddress newUser(@RequestBody UserAddress pAddress) {
        return userAddressRepository.save(pAddress);
    }

    @DeleteMapping("/{id}")
    public  void deleteUser(@PathVariable Long id) {
        //TODO: check depending data
        userAddressRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Optional<UserAddress> updateUser(@PathVariable Long id, @RequestBody UserAddress pAddress) {
        return userAddressRepository.findById(id).map(address -> {
            address.setAddress(pAddress.getAddress());
            address.setCity(pAddress.getCity());
            address.setCountry(pAddress.getCountry());
            address.setFirstName(pAddress.getFirstName());
            address.setLastName(pAddress.getLastName());
            address.setPostcode(pAddress.getPostcode());
            address.setUser(pAddress.getUser());
            return userAddressRepository.save(address);
        });
    }
}
