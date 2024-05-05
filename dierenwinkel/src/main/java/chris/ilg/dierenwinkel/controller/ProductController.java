package chris.ilg.dierenwinkel.controller;

import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.model.User;
import chris.ilg.dierenwinkel.service.ProductDto;
import chris.ilg.dierenwinkel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    @PostMapping("/add")
    public String add(@RequestBody Product p) {
        logger.info("Received new product A ZBEEEEEE: {}", p);

        productService.saveProduct(p);
        return "New product added";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable int id) {
        logger.info("FIND PRODUCT WITH ID:", id);

        Product p = productService.getProductById(id);
        if (p != null) {
            return ResponseEntity.ok().body(p); // Return 200 OK response with the user entity
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found response if user not found
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ArrayList<ProductDto>> getAll() {
        logger.info("Getting all products");

        ArrayList<Product> products = productService.getAllProduct();
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        if (!products.isEmpty()) {
            products.forEach(product -> {
                productDtos.add(new ProductDto(product));
            });
            return ResponseEntity.ok(productDtos); // Return 200 OK response with the list of products
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Return 500 Internal Server Error if products not found
        }

    }

    @GetMapping("/filter/{category}")
    public ResponseEntity<ArrayList<ProductDto>> getByCategory(@PathVariable String category) {
        logger.info("FIND PRODUCT WITH CATEGORY: " + category);
        ArrayList<Product> productOfCategory = productService.getProductsByCategory(category);
        ArrayList<ProductDto> productOfCategoryDTO = new ArrayList<>();
        productOfCategory.forEach(product -> {
            ProductDto p = new ProductDto(product);
            productOfCategoryDTO.add(p);
        });

        if (productOfCategoryDTO != null) {
            logger.info("amount of product found:" + productOfCategory.size());

            return ResponseEntity.ok().body(productOfCategoryDTO); // Return 200 OK response with the user entity
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found response if user not found
        }
    }
}