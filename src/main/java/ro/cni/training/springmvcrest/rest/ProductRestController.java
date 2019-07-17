package ro.cni.training.springmvcrest.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable Long productId,
                                                   HttpEntity httpEntity) {
        ProductModel product = productService.getProduct(productId);

        return ResponseEntity.ok(product);
    }

    @PostMapping("/imageTest")
    public ResponseEntity<Void> testImage(@RequestParam("file") MultipartFile file) {
        System.out.println(file);

        return ResponseEntity.ok(null);
    }
}
