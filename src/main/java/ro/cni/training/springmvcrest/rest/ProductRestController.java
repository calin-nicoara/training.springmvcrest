package ro.cni.training.springmvcrest.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ProductModel getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }
}
