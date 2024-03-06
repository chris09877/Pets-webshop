package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Product;
import chris.ilg.dierenwinkel.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{


    @Autowired
    private ProductRepo productRepo;
    @Override
    public Product saveProduct(Product product) {

        return productRepo.save(product);
    }
}
