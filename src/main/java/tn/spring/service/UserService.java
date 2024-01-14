package tn.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.spring.dao.UserDao;
import tn.spring.entity.User;


@Service
public class UserService {
	

	@Autowired
	
	private UserDao userDao;
	@Autowired
	public UserService(UserDao userDao) {
		super();
		this.userDao = userDao;
	}
	public List<User> allUsers() {
		return userDao.findAll();
	}
	public User  save(User user) {
		return userDao.save(user);
	}
	public Optional<User> findById(Long id) {
		return userDao.findById(id);
		}
	public void delete (Long id ) {
		
		userDao.deleteById(id);
	}


	public User addUser(User user) {
		
		return userDao.save(user);
	}
	public Optional<User> getUserById(Long id){
		return userDao.findById(id);
	}

	public Optional<User> getUserByEmail(String email){
		return userDao.findByEmail(email);
	}

}
