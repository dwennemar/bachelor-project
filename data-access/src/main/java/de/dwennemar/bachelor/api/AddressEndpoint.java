package de.dwennemar.bachelor.api;

import de.dwennemar.bachelor.dto.Address;
import de.dwennemar.bachelor.dto.Key;
import de.dwennemar.bachelor.services.KeyService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("address")
public class AddressEndpoint implements EndpointConstants {

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable Long id) {
        return getRest().getForObject(DATA_URL+ADDRESS+"/"+id, Address.class);
    }

    @PostMapping
    public Address newAddress(@RequestBody Address pAddress) {
        getRest().postForObject(KEY_URL+KEY, KeyService.newKey(KeyService.KeyScope.MARKETING,
                pAddress.getUser().getId()), Key.class);
        return getRest().postForObject(DATA_URL+ADDRESS, pAddress, Address.class);

    }

    @DeleteMapping("/{id}")
    public  void deleteAddress(@PathVariable Long id) {
        getRest().delete(DATA_URL+ADDRESS+"/"+id);
    }

    @PutMapping("/{id}")
    public void updateAddress(@PathVariable Long id, @RequestBody Address pAddress) {
        getRest().put(DATA_URL+ADDRESS+"/"+id, pAddress);
    }

    private RestTemplate getRest() {
        return new RestTemplate();
    }
}
