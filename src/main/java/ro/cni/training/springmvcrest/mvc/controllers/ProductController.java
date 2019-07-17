package ro.cni.training.springmvcrest.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.validation.Valid;

import lombok.AllArgsConstructor;
import ro.cni.training.springmvcrest.product.Product;
import ro.cni.training.springmvcrest.product.ProductRepository;

//@Controller
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

    @RequestMapping(method = RequestMethod.GET, params="brand")
    public String getProductsByBrand(@RequestParam("brand") String brand, Model model) {
        model.addAttribute("products", productRepository.findByBrand(
                brand,
                Sort.by(Sort.Order.asc("id"))
        ));

        return "product/products";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addProduct(@RequestParam("file") MultipartFile file, Product product, Model model) {
        String absolut = "/home/calin-nicoara/Projects/courses/java_courses/springmvcrest/src/main/resources/static/img";
        Product savedProduct = productRepository.save(product);

        try {
            Files.write(Paths.get(absolut, savedProduct.getId().toString() + ".jpg"), file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    public String editProduct(@PathVariable Long productId,
                              @Valid Product product, BindingResult bindingResult,
                              Model model) {
        if(bindingResult.hasErrors()) {
            product.setId(productId);
            bindingResult.rejectValue("brand", "not.good");
            model.addAttribute("product", product);
            return "product/edit";
        }

        productRepository.save(product);

        return "redirect:/product";
    }

    @GetMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId) {
        productRepository.deleteById(productId);

        return "redirect:/product";
    }
}
