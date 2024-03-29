package vn.edu.hcmuaf.fit.efootwearspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.efootwearspringboot.constants.QUERY;
import vn.edu.hcmuaf.fit.efootwearspringboot.models.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = QUERY.ORDER.FIND_ORDERS_BY_ACCOUNT_ID, nativeQuery = true)
    Optional<List<Order>> findOrdersByAccountId(Long accountId);

    @Query(value = QUERY.ORDER.FIND_ORDER_BY_ID, nativeQuery = true)
    Optional<Order> findByOrderId(String id);

    @Query(value = QUERY.ORDER.FIND_ORDERS_HOT, nativeQuery = true)
    Optional<List<Order>> findOrdersHot();

    @Query(value = QUERY.ORDER.COUNT_ORDER_BY_MONTH, nativeQuery = true)
    List<Object[]> countByMonth();

    @Query(value = QUERY.ORDER.TOTAL_ORDER_BY_MONTH, nativeQuery = true)
    List<Object[]> totalByMonth();

}
