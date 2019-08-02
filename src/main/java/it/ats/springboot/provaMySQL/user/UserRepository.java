package it.ats.springboot.provaMySQL.user;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	public Iterable<User> findByName(String name);

	public void deleteByName(String name);

}
