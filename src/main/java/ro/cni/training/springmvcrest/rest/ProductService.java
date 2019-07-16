package ro.cni.training.springmvcrest.rest;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ro.cni.training.springmvcrest.product.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


}
