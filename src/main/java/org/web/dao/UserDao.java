package org.web.dao;

import org.web.model.User;
import java.util.List;

public interface UserDao {
	void create(User user);
	List<User> getAll();
	User getById(Long id);
	void update(User user, Long id);
	void delete(Long id);

	boolean existsByEmail(String email);
}
