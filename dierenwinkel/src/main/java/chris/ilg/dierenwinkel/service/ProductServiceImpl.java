package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService{

    private ArrayList<Product> allProduct;

    @Autowired
    private ProductRepo productRepo;
    @Override
    public Product saveProduct(Product product) {

        return productRepo.save(product);
    }

    @Override
    public Product getProductById(int id) {
        return productRepo.getById(id);
    }

    @Override
    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> listOfProduct = new ArrayList<>();


        listOfProduct.addAll(productRepo.findAll());
        allProduct = listOfProduct;
        return listOfProduct;
    }

    @Override
    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> productOfCategory = new ArrayList<>();


        allProduct.forEach(product -> {
            if (product.getCategories().name().equals(category.toUpperCase())) {
                productOfCategory.add(product);
            }

        });

        return productOfCategory;



    }
}