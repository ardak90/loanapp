package kz.loanapp.dao;

import kz.loanapp.dto.LoanDto;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by ardak on 9/21/17.
 */
public interface LoanDao {

    LoanDto findLoanById(Long id);
    LoanDto persistLoan(LoanDto loan);
    LoanDto updateLoan(LoanDto loan);
    List<LoanDto> listApprovedLoans();
    List<LoanDto> listApprovedLoansByUserId(String id);
    void removeLoan(Long id);

}
