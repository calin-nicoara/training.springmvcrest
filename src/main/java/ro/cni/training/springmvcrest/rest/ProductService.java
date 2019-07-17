package ro.cni.training.springmvcrest.rest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import ro.cni.training.springmvcrest.product.Product;
import ro.cni.training.springmvcrest.product.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<ProductModel> getProduct(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::getModel);
    }

    public List<ProductListModel> getProductsForList() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::getListModel)
                .collect(Collectors.toList());
    }

    public void createProduct(ProductModel productModel) {
        Product entity = ProductMapper.getEntity(productModel);
        productRepository.save(entity);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
