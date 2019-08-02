package it.ats.springboot.provaMySQL.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	
	
}
