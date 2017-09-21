package kz.loanapp.controllers;

import kz.loanapp.dto.LoanDto;
import kz.loanapp.dto.UserDto;
import kz.loanapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ardak on 9/22/17.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity persistUser(@RequestBody UserDto userDto){

        UserDto user = userService.persistUser(userDto);
        if(user!=null){
           return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }



}
