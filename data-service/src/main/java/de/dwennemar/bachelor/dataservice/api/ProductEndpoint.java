package de.dwennemar.bachelor.dataservice.api;

import de.dwennemar.bachelor.dataservice.persist.basic.ProductRepository;
import de.dwennemar.bachelor.dataservice.persist.basic.impl.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductEndpoint {

    private final ProductRepository productRepository;

    @Autowired
    public ProductEndpoint(ProductRepository pRepository) {
        this.productRepository = pRepository;
    }

    @GetMapping("/all")
    public Iterable<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return this.productRepository.findById(id);
    }

    @PostMapping
    public Product newProduct(@RequestBody Product pProduct){
        return this.productRepository.save(pProduct);
    }

    @PutMapping("/{id}")
    public Optional<Product> updateProduct(@RequestBody Product pProduct, @PathVariable Long id) {
        return this.productRepository.findById(id).map(product -> {
            product.setDescription(pProduct.getDescription());
            product.setName(pProduct.getName());
            product.setPrice(pProduct.getPrice());
            return this.productRepository.save(product);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        this.productRepository.deleteById(id);
    }
}
