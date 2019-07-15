package ro.cni.training.springmvcrest.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import ro.cni.training.springmvcrest.product.Product;
import ro.cni.training.springmvcrest.product.ProductRepository;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/add")
    public String showAddProductForm(Product product) {
        return "product/add";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll(Sort.by(Sort.Order.asc("id"))));

        return "product/products";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addProduct(Product product, Model model) {
        productRepository.save(product);

        return "redirect:/product";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    public String getProduct(@PathVariable Long productId, Model model) {
        model.addAttribute("product", productRepository.findById(productId).get());

        return "product/product";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{productId}/edit")
    public String showProductEdit(@PathVariable Long productId, Model model) {
        //TODO Fix if product not found
        Product product = productRepository.findById(productId).get();
        model.addAttribute("product", product);

        return "product/edit";
    }

    @PostMapping("/{productId}")
    public String editProduct(@PathVariable Long productId, Product product, Model model) {
        productRepository.save(product);

        return "redirect:/product";
    }
}
