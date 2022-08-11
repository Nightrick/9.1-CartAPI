package co.grandcircus.cartapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemsController {
	
	@Autowired
	private CartItemsRepository repo;
	
	@GetMapping("/reset")
	public String resetDB() {
		
		//Delete all
		repo.deleteAll();
		
		//Add cart items to the DB
		CartItems item = new CartItems("Paper Towels", 20.50, 2);
		repo.insert(item);
		
		item = new CartItems("Pickles", 3.75, 1);
		repo.insert(item);
		
		item = new CartItems("Mustard", 1.49, 1);
		repo.insert(item);
		
		item = new CartItems("Ketchup", 1.25, 1);
		repo.insert(item);
		
		item = new CartItems("Hot Dogs", 5.05, 2);
		repo.insert(item);
		
		item = new CartItems("Hot Dog Buns", 3.50, 2);
		repo.insert(item);
		
		return "Database reset.";
	}
	
	//C(R)UD
	@GetMapping("/cart-items")
	public List<CartItems> cartItems(@RequestParam(required=false) String product, @RequestParam(required=false) Double maxPrice, @RequestParam(required=false) String prefix, @RequestParam(required=false) Integer pageSize) {
		
		if(product != null) {
			return repo.findByProduct(product);
		} else return repo.findAll();
	}
	
	//C(R)UD
	@GetMapping("/cart-items/{id}")
	public CartItems cartItemById(@PathVariable("id") String id) {
		return repo.findById(id).orElseThrow(() -> new CartItemNotFoundException(id));
	}
	
	//(C)RUD
	@PostMapping("/cart-items")
	@ResponseStatus(HttpStatus.CREATED)
	public CartItems create(@RequestBody CartItems item) {
		repo.insert(item);
		return item;
	}
	
	//CR(U)D
	@PutMapping("/cart-items/{id}")
	public CartItems updateItem(@RequestBody CartItems item, @PathVariable("id") String id) {
		item.setId(id);
		return repo.save(item);
	}
	
	//CRU(D)
	@DeleteMapping("/cart-items/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		repo.deleteById(id);
	}


}
