package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public String add(Product p){
        productService.saveProduct(p);
        return "New product added";
    }
}