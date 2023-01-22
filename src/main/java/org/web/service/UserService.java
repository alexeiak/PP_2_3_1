package org.web.service;

import org.web.model.User;
import java.util.List;

public interface UserService {
	void create(User user);
	List<User> getAll();
	User getById(Long id);
	void update(User user, Long id);
	void delete(Long id);

	boolean existsByEmail(String email);
}
