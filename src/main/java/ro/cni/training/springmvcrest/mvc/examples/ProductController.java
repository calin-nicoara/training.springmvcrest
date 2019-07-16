package ro.cni.training.springmvcrest.mvc.examples;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import lombok.AllArgsConstructor;
import ro.cni.training.springmvcrest.mvc.product.Product;
import ro.cni.training.springmvcrest.mvc.product.ProductRepository;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> all = productRepository.findAll();
        model.addAttribute("products", all);

        return "productList";
    }

    @GetMapping("/{productId}")
    public String getProduct(@PathVariable Long productId, Model model) {
        Product product = productRepository.findById(productId).get();
        model.addAttribute("product", product);

        return "productView";
    }
}
