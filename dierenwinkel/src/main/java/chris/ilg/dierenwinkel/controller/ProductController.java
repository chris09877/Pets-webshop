package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    @PostMapping("/add")
    public String add(@RequestBody Product p){
        logger.info("Received new product A ZBEEEEEE: {}", p);

        productService.saveProduct(p);
        return "New product added";
    }
}