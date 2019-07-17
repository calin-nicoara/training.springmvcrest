package ro.cni.training.springmvcrest.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductModel> getProduct(@PathVariable Long productId) {
        Optional<ProductModel> optionalProductModel =
                productService.getProduct(productId);

//        if(optionalProductModel.isPresent()) {
//            return ResponseEntity.ok(optionalProductModel.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }

        return optionalProductModel
                .map(productModel -> ResponseEntity.ok(productModel))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ProductListModel> getProductsForList() {
        return productService.getProductsForList();
    }

    @PostMapping
    public void createProduct(@RequestBody ProductModel productModel) {
        productService.createProduct(productModel);
    }

    @PutMapping("/{productId}")
    public void updateProduct(@RequestBody ProductModel productModel,
                              @PathVariable Long productId) {
        productModel.setId(productId);
        productService.createProduct(productModel);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
