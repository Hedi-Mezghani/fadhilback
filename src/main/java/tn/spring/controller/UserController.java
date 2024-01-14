package tn.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tn.spring.entity.User;
import tn.spring.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

	private UserService userService;

	@GetMapping("/allUsers")
	public List<User> getAllUsers() {
		return userService.allUsers();
	}

	@PostMapping("/save")
	public User save(@RequestBody User user) {
		return userService.save(user);
	}

	@PostMapping("/adduser")
	public User addUser(@ModelAttribute User user) {
		//System.out.println("Données reçues côté serveur : " + user.toString());
		return userService.addUser(user);
	}
	@DeleteMapping("/user/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		userService.delete(id);
	}

	@GetMapping("/getuserbyid/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		Optional<User> user= userService.getUserById(id);
		return  user.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
	}

	@GetMapping("/getuserbyEmail/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email){
		Optional<User> user= userService.getUserByEmail(email);
		return  user.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
	}


}
