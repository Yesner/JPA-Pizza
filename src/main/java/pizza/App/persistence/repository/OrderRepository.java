package pizza.App.persistence.repository;


import org.springframework.data.repository.ListCrudRepository;
import pizza.App.persistence.entity.OrderEntity;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
}
