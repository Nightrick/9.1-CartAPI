package co.grandcircus.cartapi;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

//Every collection in your database that you're creating an API endpoint for requires its own repo(i.e. users, favorites, etc...)
public interface CartItemsRepository extends MongoRepository<CartItems, String>{
	
	List<CartItems> findByProduct(String product);
	
	Optional<CartItems> findById(String id);
	
	void deleteById(String id);

}
