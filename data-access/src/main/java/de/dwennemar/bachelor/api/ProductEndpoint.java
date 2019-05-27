package de.dwennemar.bachelor.api;

import de.dwennemar.bachelor.dto.Product;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/product")
public class ProductEndpoint implements EndpointConstants {

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return getRest().getForObject(DATA_URL+PRODUCT+"/"+id, Product.class);
    }

    @PostMapping
    public Product newProduct(@RequestBody Product pProduct){
        return getRest().postForObject(DATA_URL+PRODUCT, pProduct, Product.class);
    }

    @PutMapping("/{id}")
    public void updateProduct(@RequestBody Product pProduct, @PathVariable Long id) {
        getRest().put(DATA_URL+PRODUCT+"/"+id, pProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        getRest().delete(DATA_URL+PRODUCT+"/"+id);
    }

    private RestTemplate getRest() {
        return new RestTemplate();
    }
}
