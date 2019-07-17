package ro.cni.training.springmvcrest.rest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    private final MessageSource messageSource;

//    @GetMapping("/{productId}")
//    public ResponseEntity<ProductModel> getProduct(@PathVariable Long productId) {
//        Optional<ProductModel> optionalProductModel =
//                productService.getProduct(productId);
//
////        if(optionalProductModel.isPresent()) {
////            return ResponseEntity.ok(optionalProductModel.get());
////        } else {
////            return ResponseEntity.notFound().build();
////        }
//
//        return optionalProductModel
//                .map(productModel -> ResponseEntity.ok(productModel))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductModel> getProduct(
            @PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
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
    public ResponseEntity<Object> createProduct(
            @RequestBody @Valid ProductModel productModel,
            BindingResult bindingResult,
            @RequestHeader("username") String username,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        System.out.println(image);
        return handleCreatOrUpdateProduct(productModel, bindingResult, username);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductModel productModel,
                              @PathVariable Long productId,
                              BindingResult bindingResult,
                                                @RequestHeader("username") String username) {
        productModel.setId(productId);
        return handleCreatOrUpdateProduct(productModel, bindingResult, username);
    }

    private ResponseEntity<Object> handleCreatOrUpdateProduct(
            final ProductModel productModel,
            final BindingResult bindingResult,
            final String username) {
        if(bindingResult.hasErrors()) {
            List<ErrorModel> errorModels = bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> new ErrorModel(
                            fieldError.getField(),
                            fieldError.getCode(),
                            messageSource.getMessage(
                                    fieldError,
                                    LocaleContextHolder.getLocale())))
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errorModels);
        }

        productService.createProduct(productModel, username);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
