package ro.cni.training.springmvcrest.rest;

import ro.cni.training.springmvcrest.product.Product;

public class ProductMapper {

    public ProductModel toModel(Product product) {
        return ProductModel.builder()
                .id(product.getId())
                .brand(product.getBrand())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public Product toEntity(ProductModel productModel) {
        return Product.builder()
                .id(productModel.getId())
                .brand(productModel.getBrand())
                .name(productModel.getName())
                .price(productModel.getPrice())
                .build();
    }
}
