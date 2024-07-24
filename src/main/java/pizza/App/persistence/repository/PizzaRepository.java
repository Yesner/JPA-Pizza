package pizza.App.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import pizza.App.persistence.entity.PizzaEntity;
import pizza.App.service.dto.UpdatePizzaPriceDto;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);
    int countByVeganTrue();


    // @Modifying en un @Query
    @Query(value =
            "UPDATE pizza " +
                    "SET price = :#{#newPizzaPrice.newPrice} " +
                    "WHERE id_pizza= :#{#newPizzaPrice.pizzaId}", nativeQuery = true)

    @Modifying // es necesario, de lo contrario dara un error acompañado de @Transactional en el servicio
    void updatePrice(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);


}
