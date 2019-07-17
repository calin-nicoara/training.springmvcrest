package ro.cni.training.springmvcrest.rest;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.cni.training.springmvcrest.product.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductModel getProduct(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::toModel)
                .orElseThrow(() -> new RuntimeException("Not found!!"));
    }
}
