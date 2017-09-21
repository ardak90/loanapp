package kz.loanapp.dto;

import kz.loanapp.models.Loan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ardak on 9/21/17.
 */
public class LoanDto {

    public Long id;
    public Date dateApplied;
    public Date termDate;
    public BigDecimal amount;
    public String countryFrom;
    public boolean isApproved;
    public UserDto userDto;

    public static LoanDto fromLoan(Loan loan){
        LoanDto loanDto = new LoanDto();
        loanDto.id = loan.getId();
        loanDto.amount = loan.getAmount();
        loanDto.countryFrom = loan.getCountryFrom();
        loanDto.dateApplied = loan.getDateApplied();
        loanDto.termDate = loan.getTermDate();
        loanDto.isApproved = loan.isApproved();
        loanDto.userDto = UserDto.fromUser(loan.getUser());
        return loanDto;
    }

    public static Loan toLoan(LoanDto loanDto){
        if(loanDto == null) {
            return null;
        } else {
            Loan loan = new Loan();
            loan.setId(loanDto.id);
            loan.setAmount(loanDto.amount);
            loan.setCountryFrom(loanDto.countryFrom);
            loan.setDateApplied(loanDto.dateApplied);
            loan.setTermDate(loanDto.termDate);
            loan.setApproved(loanDto.isApproved);
            loan.setUser(UserDto.toUser(loanDto.userDto));
            return loan;
        }
    }

    public static List<LoanDto> fromLoans(List<Loan> loans){
        return loans.stream().map(l -> fromLoan(l)).collect(Collectors.toList());
    }

}
