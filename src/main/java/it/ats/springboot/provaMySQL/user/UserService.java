package it.ats.springboot.provaMySQL.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public Iterable<User> getByName(String name) {
		return userRepository.findByName(name);
	}
	
	public Iterable<User> getAll(){
		return userRepository.findAll();
	}
	
	public boolean add(User user) {
		if (userRepository.findByEmail(user.getEmail()) == null) {
			userRepository.save(user);
			return true;
		}
		
		return false;
	}
	
	public void add(Iterable<User> users) {
		userRepository.saveAll(users);
	}
	
	public boolean update(Integer id, String name, String email) {
		if (userRepository.existsById(id)) {
			User user = userRepository.findById(id).orElse(null);
			user.setName(name);
			user.setEmail(email);
			userRepository.save(user);
			return true;
		}

		return false;
	}
	
	public boolean update(Integer id, User updatedUser) {
		if (userRepository.existsById(id)) {
			User user = userRepository.findById(id).orElse(null);
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			userRepository.save(user);
			return true;
		}

		return false;
	}
	
	public boolean delete(Integer id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
	
	public boolean deleteByName(String name) {
		List<User> usersFound = (List<User>) userRepository.findByName(name);
		if (usersFound.isEmpty()) {
			return false;
		} else {
			userRepository.deleteByName(name);
			return true;
		}
	}
	
}
