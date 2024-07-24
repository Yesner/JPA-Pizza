package pizza.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizza.App.persistence.entity.PizzaEntity;
import pizza.App.service.PizzaService;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired

    public PizzaController(PizzaService pizzaService)
    {
        this.pizzaService = pizzaService;
    }

    // Query all
    // Sin paginar
    /*@GetMapping
    public ResponseEntity<List<PizzaEntity>>getAll()
    {
        return ResponseEntity.ok(this.pizzaService.getAll());
    }*/

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>>getAll(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "8") int elements)
    {
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    // Query by id
    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity>get(@PathVariable int idPizza)
    {
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    // Query by available sin paginar
    /*@GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>>getAvailable()
    {
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }*/

    //Paginado
    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>>getAvailable(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "8") int elements,
                                                         @RequestParam(defaultValue = "price") String sortBy,
                                                         @RequestParam(defaultValue = "ASC") String sortDirection)
    {
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy,sortDirection));
    }

    // Query by name
    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity>getByName(@PathVariable String name)
    {
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    // Query by ingredient
    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>>getWith(@PathVariable String ingredient)
    {
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    // Query by without ingredient
    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>>getWithout(@PathVariable String ingredient)
    {
        return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));
    }

    // Query by without ingredient
    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>>getCheapest(@PathVariable Double price)
    {
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }


    //New
    @PostMapping
    public ResponseEntity<PizzaEntity>add(@RequestBody PizzaEntity pizza)
    {
        if (pizza.getIdPizza()==null || !this.pizzaService.exists(pizza.getIdPizza())) {

                return ResponseEntity.ok(this.pizzaService.save(pizza));
            }

            return ResponseEntity.badRequest().build();
        }

        //Update
    @PutMapping
    public ResponseEntity<PizzaEntity>update(@RequestBody PizzaEntity pizza)
    {
        if (pizza.getIdPizza()!=null && this.pizzaService.exists(pizza.getIdPizza())) {

            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }

        return ResponseEntity.badRequest().build();
    }

    // Delete

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza)
    {
        if (this.pizzaService.exists(idPizza))
        {
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }



}




