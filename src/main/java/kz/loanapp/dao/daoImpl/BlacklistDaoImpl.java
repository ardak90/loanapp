package kz.loanapp.dao.daoImpl;

import kz.loanapp.dao.BlacklistDao;
import kz.loanapp.dto.BlacklistDto;
import kz.loanapp.models.Blacklist;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by ardak on 9/22/17.
 */

@Repository
public class BlacklistDaoImpl implements BlacklistDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public BlacklistDto persisToBlacklist(BlacklistDto blacklist) {
        Blacklist l = BlacklistDto.toBlacklist(blacklist);
        em.persist(l);
        return BlacklistDto.fromBlacklist(l);
    }

    @Override
    public void removeFromBlacklist(String userId) {

        List<Blacklist> l = em.createQuery("SELECT b FROM Blacklist b WHERE b.user.id = :username")
                .setParameter("username", userId).getResultList();
        if(l.size()>0){
        em.remove(em.getReference(Blacklist.class, l.get(0).getId()));
        }
    }

    @Override
    public boolean isInBlacklist(String userId) {
        List<Blacklist> l = em.createQuery("SELECT b FROM Blacklist b WHERE b.user.id = :username")
                .setParameter("username", userId).getResultList();
        if(l.size()>0){
            return true;
        } else {
            return false;
        }
    }
}
