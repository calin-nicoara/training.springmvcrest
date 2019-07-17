package ro.cni.training.springmvcrest.rest;

import ro.cni.training.springmvcrest.product.Product;

public class ProductMapper {

    public static ProductModel getModel(Product product) {
        return ProductModel.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .category(product.getCategory())
                .price(product.getPrice())
                .build();
    }

    public static Product getEntity(ProductModel productModel) {
        return Product.builder()
                .id(productModel.getId())
                .name(productModel.getName())
                .brand(productModel.getBrand())
                .category(productModel.getCategory())
                .price(productModel.getPrice())
                .build();
    }

    public static ProductListModel getListModel(Product product) {
        return ProductListModel.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }
}
