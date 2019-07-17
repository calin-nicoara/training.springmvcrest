package ro.cni.training.springmvcrest.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBrand(String brandName, Sort sort);

    Page<Product> findByBrand(String brandName, Pageable pageRequest);

    Page<Product> findByName(String productName, Pageable pageRequest);
}
