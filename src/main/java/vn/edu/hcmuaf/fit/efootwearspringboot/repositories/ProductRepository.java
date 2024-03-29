package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = QUERY.PRODUCT.FIND_PRODUCTS, nativeQuery = true)
    Optional<List<Product>> findProducts();
    @Query(value = QUERY.PRODUCT.FIND_PRODUCTS_HOT, nativeQuery = true)
    Optional<List<Product>> findProductsHot();
    @Query(value = QUERY.PRODUCT.FIND_PRODUCTS_NEWS, nativeQuery = true)
    Optional<List<Product>> findProductsNew();

    @Query(value = QUERY.PRODUCT.FIND_PRODUCT_BY_ID, nativeQuery = true)
    Optional<Product> findProductById(Long id);

    @Query(value = QUERY.PRODUCT.FIND_PRODUCT_BY_SLUG_COLOR, nativeQuery = true)
    Optional<Product> findProduct(String slug, Long color_id);

    @Query(value = QUERY.PRODUCT.FIND_PRODUCT_BY_SLUG, nativeQuery = true)
    Optional<List<Product>> findProductBySlug(String slug);

    @Query(value = QUERY.PRODUCT.FIND_PRODUCT_BY_CATE_SLUG,
            nativeQuery = true)
    Optional<List<Product>> findProductByCateSlug(String slug);

    @Query(countQuery = QUERY.PRODUCT.COUNT_PRODUCT_BY_SLUG)
    Integer countProductBySlug(String slug);

    @Query(value = QUERY.PRODUCT.FIND_PRODUCT_BY_NAME,
            nativeQuery = true)
    Optional<List<Product>> findProductsByName(String query);
}
