package kz.loanapp.services;

import kz.loanapp.dao.LoanDao;
import kz.loanapp.dao.UserDao;
import kz.loanapp.dto.LoanDto;
import kz.loanapp.dto.UserDto;
import kz.loanapp.models.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by ardak on 9/21/17.
 */
@Transactional
@Service
public class LoanService {

    @Autowired
    LoanDao loanDao;

    public List<LoanDto> listApprovedLoansByUserId(String id){
        return loanDao.listApprovedLoansByUserId(id);
    }

    public List<LoanDto> listApprovedLoans(){
        return loanDao.listApprovedLoans();
    }

    public LoanDto persistLoan(LoanDto loan) {
        return loanDao.persistLoan(loan);
    }

    public LoanDto updateLoan(LoanDto loan){
        return loanDao.updateLoan(loan);
    }

    public LoanDto findLoanById(Long id){
        return loanDao.findLoanById(id);
    }

    public int getCountAppInTimeframe(Date dateNow, Long secondTimeframe, String countryFrom){
        return loanDao.getCountAppInTimeframe(dateNow, secondTimeframe, countryFrom);
    }
}
