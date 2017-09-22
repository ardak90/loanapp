package kz.loanapp.controllers;

import kz.loanapp.dto.LoanDto;
import kz.loanapp.facades.LoanServiceFacade;
import kz.loanapp.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ardak on 9/21/17.
 */
@RestController
@RequestMapping(value = "/loans")
public class LoanController {

    @Autowired
    LoanServiceFacade loanServiceFacade;

    @Autowired
    LoanService loanService;


    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ResponseEntity createLoan(@RequestBody LoanDto loanDto, @PathVariable String userId, HttpServletRequest request){
        LoanDto loan = loanServiceFacade.persistLoan(loanDto, userId, request);
        if(loan!=null){
            return new ResponseEntity(loan, HttpStatus.OK);
        }
        else {
            return new ResponseEntity("You can't apply for loan, please contact our manager in your country. Thank You", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/approved", method = RequestMethod.GET)
    public ResponseEntity listApprovedLoans(){
        List<LoanDto> loans = loanService.listApprovedLoans();
        return new ResponseEntity(loans, HttpStatus.OK);
    }

    @RequestMapping(value = "/approved/{userId}", method = RequestMethod.GET)
    public ResponseEntity listApprovedLoans(@PathVariable String userId){
        List<LoanDto> loans = loanService.listApprovedLoansByUserId(userId);
        return new ResponseEntity(loans, HttpStatus.OK);
    }

    @RequestMapping(value = "/approve/{loanId}", method = RequestMethod.GET)
    public ResponseEntity approveLoan(@PathVariable Long loanId){
        LoanDto loan = loanService.findLoanById(loanId);
        if(loan!=null){
            loan.isApproved = true;
            loan = loanService.updateLoan(loan);
            return new ResponseEntity(loan, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


}
