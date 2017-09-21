package kz.loanapp.dao.daoImpl;

import kz.loanapp.dao.UserDao;
import kz.loanapp.dto.UserDto;
import kz.loanapp.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ardak on 9/22/17.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserDto findByUserId(String id) {
        return UserDto.fromUser(em.find(User.class, id));
    }

    @Override
    public UserDto persistUser(UserDto user) {
        User u = UserDto.toUser(user);
        em.persist(u);
        return UserDto.fromUser(u);
    }
}
