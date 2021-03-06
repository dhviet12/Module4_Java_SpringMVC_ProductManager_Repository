package service.product;

import exception.NotFound;
import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.IProductRepository;
import service.product.IProductService;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return  productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAllByOrderByIdAsc(pageable);
    }

    @Override
    public Product findById(Long id) throws NotFound {
        Product product = productRepository.findOne(id);
        if (product != null){
            return product;
        }
        else throw new NotFound();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);

    }

    @Override
    public List<Product> findProductName(String name) {
        name = "%"+ name +"%";
        return productRepository.findProductName(name);
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> findTop5ByPrice() {
        return productRepository.findTop5ByOrderByPriceDesc();
    }

    @Override
    public List<Product> findTop5ByDate() {
        return productRepository.findTop5ByDate();
    }


}
