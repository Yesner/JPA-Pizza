package pizza.App.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pizza.App.persistence.entity.PizzaEntity;
import pizza.App.persistence.repository.PizzaPagSortRepository;
import pizza.App.persistence.repository.PizzaRepository;

import java.util.List;

@Service

public class PizzaService {

    //Para consultas con query
    //private final JdbcTemplate jdbcTemplate;

    private final PizzaRepository pizzaRepository;

    private final PizzaPagSortRepository PizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, pizza.App.persistence.repository.PizzaPagSortRepository pizzaPagSortRepository)
    {
        this.pizzaRepository = pizzaRepository;
        PizzaPagSortRepository = pizzaPagSortRepository;
    }


    // Para consultas con query
    /*public List<PizzaEntity> getAll()
    {
        return this.jdbcTemplate.query("SELECT * FROM pizza", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }*/

    // Con repositorio
    // Sin paginar
    /*public List<PizzaEntity> getAll()
    {
        return this.pizzaRepository.findAll();
    }*/

    //Paginado
    public Page<PizzaEntity> getAll(int page, int elements)
    {
        Pageable pageRequest = PageRequest.of(page,elements);
        return this.PizzaPagSortRepository.findAll(pageRequest);
    }

    public PizzaEntity get(int idPizza)
    {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    // sin paginar
    /*public List<PizzaEntity> getAvailable()

    {
        System.out.println(this.pizzaRepository.countByVeganTrue());
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }*/

    //paginado
    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection)

    {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);

        Pageable pageRequest = PageRequest.of(page,elements, sort);


        //aca no esta el atributo
        //Pageable pageRequest = PageRequest.of(page,elements, Sort.by(sortBy));
       // System.out.println(this.pizzaRepository.countByVeganTrue());
        return this.PizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name)
    {
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWith(String ingredient)
    {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient)
    {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(Double price)
    {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }


    public PizzaEntity save(PizzaEntity pizza)
    {
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza)
    {
        this.pizzaRepository.deleteById(idPizza);
    }

    public Boolean exists(int idPizza)
    {
        return this.pizzaRepository.existsById(idPizza);
    }
}
