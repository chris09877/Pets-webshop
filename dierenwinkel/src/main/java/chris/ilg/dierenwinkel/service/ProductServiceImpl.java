package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService{


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
        return listOfProduct;
    }
/*
    @Override
    public ArrayList<Product> getProductsByCategory() {
        ArrayList<Product> listOfProduct = new ArrayList<>();
        listOfProduct.addAll(productRepo.findAll());
        return listOfProduct;

    }*/
}