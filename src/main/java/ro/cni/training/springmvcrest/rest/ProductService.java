package ro.cni.training.springmvcrest.rest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import ro.cni.training.springmvcrest.product.Product;
import ro.cni.training.springmvcrest.product.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductModel getProduct(Long id) {
        return productRepository.findById(id)
                .map(ProductMapper::getModel)
                .orElseThrow(() -> new NotFoundException());
    }

    public List<ProductListModel> getProductsForList(
            final ProductFilterModel filterModel) {
        return getProductsFiltered(filterModel)
                .stream()
                .map(ProductMapper::getListModel)
                .collect(Collectors.toList());
    }

    public List<Product> getProductsFiltered(ProductFilterModel filterModel) {
        PageRequest pageRequest = PageRequest
                .of(filterModel.getPage(),
                        filterModel.getSize(),
                        Sort.by(Sort.Order.asc(filterModel.getSort()))
                );

        if (filterModel.getBrand() != null) {
            return productRepository.findByBrand(filterModel.getBrand(), pageRequest).getContent();
        } else if (filterModel.getName() != null) {
            return productRepository.findByName(filterModel.getName(), pageRequest).getContent();
        } else {
            return productRepository.findAll(pageRequest).getContent();
        }
    }

    public void createProduct(ProductModel productModel, final String username) {
        Product entity = ProductMapper.getEntity(productModel, username);
        productRepository.save(entity);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
