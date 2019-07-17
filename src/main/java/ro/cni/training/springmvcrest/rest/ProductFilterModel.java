package ro.cni.training.springmvcrest.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterModel {
     private Integer page = 0;
     private Integer size = 10;
     private String sort = "id";
     private String brand;
     private String name;
}
