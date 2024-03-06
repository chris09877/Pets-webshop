package chris.ilg.dierenwinkel.service;

import chris.ilg.dierenwinkel.model.Product;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    @Transactional
    public Product saveProduct(Product product);
}
