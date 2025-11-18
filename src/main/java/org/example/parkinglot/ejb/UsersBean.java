package org.example.parkinglot.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.parkinglot.entities.User;
import org.parkinglot.parkinglot.common.UserDto;

import java.util.List;
import java.util.logging.Logger;

@Stateless

public class UsersBean {
    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public List<UserDto> findAllUsers() {
        LOG.info("Finding all users");
        try {
            var typedQuery = entityManager.createQuery("SELECT u FROM User u", org.example.parkinglot.entities.User.class);
            List<org.example.parkinglot.entities.User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<UserDto> copyUsersToDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()
                ))
                .toList();
    }
}