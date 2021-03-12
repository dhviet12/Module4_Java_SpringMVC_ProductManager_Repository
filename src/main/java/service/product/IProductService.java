package service.product;

import model.Category;
import model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.IService;

import java.util.List;

public interface IProductService extends IService<Product> {

    Page<Product> findAll(Pageable pageable);

    List<Product> findProductName(String name);

    List<Product> findAllByCategory(Category category);

    List<Product> findTop5ByPrice();

    List<Product> findTop5ByDate();


}
