package pizza.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizza.App.persistence.entity.CustomerEntity;
import pizza.App.persistence.repository.CustomerRepository;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity>getByPhone(@PathVariable String phone)
    {
        return ResponseEntity.ok(customerRepository.findByPhone(phone));
    }


}
