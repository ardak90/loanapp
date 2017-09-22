package kz.loanapp.dao.daoImpl;

import kz.loanapp.dao.LoanDao;
import kz.loanapp.dto.LoanDto;
import kz.loanapp.dto.UserDto;
import kz.loanapp.models.Loan;
import kz.loanapp.models.User;
import kz.loanapp.utils.DateUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ardak on 9/21/17.
 */
@Repository

public class LoanDaoImpl implements LoanDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public LoanDto persistLoan(LoanDto loan) {
        Loan l = LoanDto.toLoan(loan);
        em.persist(l);
        return LoanDto.fromLoan(l);
    }

    @Override
    public LoanDto updateLoan(LoanDto loan) {
        Loan l = LoanDto.toLoan(loan);
        em.merge(l);
        return LoanDto.fromLoan(l);
    }

    @Override
    public LoanDto findLoanById(Long id) {
        return LoanDto.fromLoan(em.find(Loan.class, id));
    }

    @Override
    public List<LoanDto> listApprovedLoans() {
        List<Loan> loans = em.createQuery("SELECT l FROM Loan l WHERE l.isApproved = true").getResultList();
        return LoanDto.fromLoans(loans);
    }

    @Override
    public List<LoanDto> listApprovedLoansByUserId(String id) {
        List<Loan> loans = em.createQuery("SELECT l FROM Loan l WHERE l.isApproved = true AND l.user.id = :username")
            .setParameter("username", id).getResultList();
        return LoanDto.fromLoans(loans);
    }

    @Override
    public void removeLoan(Long id) {
        em.remove(em.getReference(Loan.class, id));
    }

    @Override
    public int getCountAppInTimeframe(Date dateNow, Long secondTimeframe, String countryFrom){
        Date timeframeDate = DateUtils.getTimeFrameDate(dateNow, secondTimeframe);
        long count = (Long) em.createQuery("SELECT count(l) FROM Loan l WHERE l.countryFrom = :countryfrom AND l.dateApplied>:timeframedate AND l.dateApplied<:datenow ")
                .setParameter("countryfrom", countryFrom)
                .setParameter("timeframedate", timeframeDate)
                .setParameter("datenow", dateNow).getSingleResult();

        return (int)count;
    }

}
