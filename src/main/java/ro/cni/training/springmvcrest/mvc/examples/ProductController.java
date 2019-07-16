package ro.cni.training.springmvcrest.mvc.examples;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.AllArgsConstructor;
import ro.cni.training.springmvcrest.mvc.product.Product;
import ro.cni.training.springmvcrest.mvc.product.ProductRepository;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping(params = "brand")
    public String getByBrand(
            @RequestParam(value = "brand", required = false) String brand,
            Model model) {
        model.addAttribute("products",
                productRepository.findByBrand(brand));

        return "productList";
    }

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products",
                productRepository.findAll());

        return "productList";
    }

    @GetMapping("/{productId}")
    public String getProduct(@PathVariable Long productId, Model model) {
        Product product = productRepository.findById(productId).get();
        model.addAttribute("product", product);

        return "productView";
    }

    @GetMapping("/add")
    public String showAddProduct(Product product) {
        return "addProduct";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(value = "image", required = false) MultipartFile file,
            Product product) {
        saveProductCommon(product, file);

        return "redirect:/product";
    }

    @PostMapping("/{productId}/edit")
    public String editProduct(
            @PathVariable("productId") Long id,
            @RequestParam(value = "image", required = false) MultipartFile file,
            Product product) {
        product.setId(id);
        saveProductCommon(product, file);

        return "redirect:/product";
    }

    private void saveProductCommon(final Product product,
                                   final MultipartFile file) {
        Product savedProduct = productRepository.save(product);

        Path path = Paths.get("/home/calin-nicoara/Projects/courses/java_courses/springmvcrest/" +
                "src/main/resources/static/img", savedProduct.getId().toString());

        if(file != null) {
            try {
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/{productId}/edit")
    public String showEditProduct(@PathVariable Long productId,
                                  Model model) {
        Product product = productRepository.findById(productId).get();
        model.addAttribute("product", product);

        return "editProduct";
    }

    @GetMapping("/{productId}/delete")
    public String delete(@PathVariable Long productId) {
        productRepository.deleteById(productId);

        return "redirect:/product";
    }
}
