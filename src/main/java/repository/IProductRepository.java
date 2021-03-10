package repository;

import model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {

    //Tìm kiếm sản phẩm theo tên
    @Query(value = "select * " +
            "from product " +
            "where product.name like ?", nativeQuery = true)
    List<Product> findProductName(String name);


}
