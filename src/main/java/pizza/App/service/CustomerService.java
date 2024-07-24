package pizza.App.service;

import org.springframework.stereotype.Service;
import pizza.App.persistence.entity.CustomerEntity;
import pizza.App.persistence.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findByPhone(String phone)
    {
        return this.customerRepository.findByPhone(phone);
    }
}
