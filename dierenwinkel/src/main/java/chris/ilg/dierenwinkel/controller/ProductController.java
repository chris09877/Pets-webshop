package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<Product> get (@PathVariable int id)
    {
        logger.info("FIND PRODUCT WITH ID:", id);

        Product p  = productService.getProductById(id);
        if (p != null) {
            return ResponseEntity.ok().body(p); // Return 200 OK response with the user entity
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found response if user not found
        }
    }
}