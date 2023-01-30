package org.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web.dao.UserDao;
import org.web.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

	@Transactional
	@Override
	public void create(User user) {
		userDao.create(user);
	}

	@Transactional
	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Transactional
	@Override
	public User getById(Long id) {
		return userDao.getById(id);
	}

	@Transactional
	@Override
	public void update(User user, Long id) {
		userDao.update(user, id);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}


	@Transactional
	@Override
	public boolean existsByEmail(String email) {
		return userDao.existsByEmail(email);
	}
}
