package org.web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void create(User user) {
		entityManager.persist(user);
	}

	@Override
	public List<User> getAll() {
		String query = "SELECT u FROM User u";
		return entityManager.createQuery(query, User.class).getResultList();
	}

	@Override
	public User getById(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void update(User user, Long id) {
		User userToBeUpdated = entityManager.find(User.class, id);
		userToBeUpdated.setName(user.getName());
		userToBeUpdated.setLastname(user.getLastname());
		userToBeUpdated.setEmail(user.getEmail());

		entityManager.merge(userToBeUpdated);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(getById(id));
	}


	@Override
	public boolean existsByEmail(String email) {
		String query = "SELECT COUNT(u.id) FROM User u " +
				               "WHERE u.email = :email";
		Long count = entityManager.createQuery(query, Long.class)
				            .setParameter("email", email)
				            .getSingleResult();

		return count >= 1;
	}
}
