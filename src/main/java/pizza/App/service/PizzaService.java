package pizza.App.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.App.persistence.entity.PizzaEntity;
import pizza.App.persistence.repository.PizzaRepository;

import java.util.List;

@Service

public class PizzaService {

    //Para consultas con query
    //private final JdbcTemplate jdbcTemplate;

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository)
    {
        this.pizzaRepository = pizzaRepository;
    }


    // Para consultas con query
    /*public List<PizzaEntity> getAll()
    {
        return this.jdbcTemplate.query("SELECT * FROM pizza", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }*/

    // Con repositorio
    public List<PizzaEntity> getAll()
    {
        return this.pizzaRepository.findAll();
    }

    public PizzaEntity get(int idPizza)
    {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public List<PizzaEntity> getAvailable()
    {
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
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
