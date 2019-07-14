package ro.cni.training.springmvcrest.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import ro.cni.training.springmvcrest.product.ProductRepository;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());

        return "product/products";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    public String getProduct(@PathVariable Long productId, Model model) {
        model.addAttribute("product", productRepository.findById(productId).get());

        return "product/product";
    }
}
