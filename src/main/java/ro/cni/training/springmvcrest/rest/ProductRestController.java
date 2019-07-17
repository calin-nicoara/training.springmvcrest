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

//    @GetMapping
//    public List<ProductListModel> getProductsForList(
//            @RequestParam(value = "page",
//                    required = false, defaultValue = "0") Integer page,
//            @RequestParam(value = "size",
//            required = false, defaultValue = "10") Integer size,
//            @RequestParam(value = "sort",
//                    required = false, defaultValue = "id") String sort,
//            @RequestParam(value="brand", required = false) String brand,
//            @RequestParam(value="name", required = false) String name
//    ) {
//        return productService.getProductsForList(page, size, sort, brand, name);
//    }

    @GetMapping
    public List<ProductListModel> getProductsForList(
            @ModelAttribute ProductFilterModel productFilterModel
    ) {
        return productService.getProductsForList(productFilterModel);
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
