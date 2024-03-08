package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface ProductService {

    @Transactional
    public Product saveProduct(Product product);
    public Product getProductById(int id);
    public ArrayList<Product> getAllProduct();
    //public ArrayList<Product> getProductsByCategory(String category);

}