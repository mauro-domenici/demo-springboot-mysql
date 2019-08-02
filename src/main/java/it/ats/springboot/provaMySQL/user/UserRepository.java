package it.ats.springboot.provaMySQL.user;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	public Iterable<User> findByName(String name);

	public User findByEmail(String email);
	
	public void deleteByName(String name);
	
}
