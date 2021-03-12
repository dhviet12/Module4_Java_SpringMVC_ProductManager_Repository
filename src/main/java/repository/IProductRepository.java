package repository;

import model.Category;
import model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {


    Page<Product> findAllByOrderByIdAsc(Pageable pageable);

    List<Product> findTop5ByOrderByPriceDesc();

    @Query(value = "select * from product order by date desc limit 5;", nativeQuery = true)
    List<Product> findTop5ByDate();

    @Query(value = "select * " +
            "from product " +
            "where product.name like ?1 ", nativeQuery = true)
    List<Product> findProductName(String name);

    List<Product> findAllByCategory(Category category);


}
