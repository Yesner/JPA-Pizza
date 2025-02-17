package pizza.App.persistence.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import pizza.App.persistence.entity.CustomerEntity;

public interface CustomerRepository  extends ListCrudRepository<CustomerEntity, String> {


    @Query("SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    CustomerEntity findByPhone(@Param("phone") String phone);
}
