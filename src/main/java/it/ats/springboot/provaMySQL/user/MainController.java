package it.ats.springboot.provaMySQL.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class MainController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user/{id}") 
	public @ResponseBody User getUserById(@PathVariable Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	@GetMapping("/user")
	public @ResponseBody Iterable<User> getUserByName(@RequestParam String name){
		return userRepository.findByName(name);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}
	
//	@GetMapping(path="/add")
//	public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
//		
//		User u = new User();
//		u.setEmail(email);
//		u.setName(name);
//		userRepository.save(u);
//		
//		return "New user saved";
//	}
	
	@PostMapping("/user")
	public @ResponseBody String addNewUser(@RequestBody User newUser) {
		userRepository.save(newUser);
		return "New user saved";
	}
	
	@PostMapping("/users")
	public @ResponseBody String addNewUsers(@RequestBody Iterable<User> newUsers) {
		userRepository.saveAll(newUsers);
		return "New users saved";
	}
	
	@PutMapping("/user")
	public @ResponseBody String updateUser(@RequestParam Integer id, @RequestParam String name, @RequestParam String email) {
		if (userRepository.existsById(id)) {
			User user = userRepository.findById(id).orElse(null);
			user.setName(name);
			user.setEmail(email);
			userRepository.save(user);
			return "User updated";
		}

		return "User doesn't exist";
	}
	
	@PutMapping("/user/{id}")
	public @ResponseBody String updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
		if (userRepository.existsById(id)) {
			User user = userRepository.findById(id).orElse(null);
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			userRepository.save(user);
			return "User updated";
		}

		return "User doesn't exist";
	}
	
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable Integer id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "User deleted";
		}
		
		return "User doesn't exist";
	}
	
	@DeleteMapping("/user")
	public @ResponseBody String deleteByName(@RequestParam String name) {
		userRepository.deleteByName(name);
		return "User deleted";
	}
}
