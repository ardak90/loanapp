package kz.loanapp.controllers;

import kz.loanapp.dto.BlacklistDto;
import kz.loanapp.dto.UserDto;
import kz.loanapp.services.BlacklistService;
import kz.loanapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by ardak on 9/22/17.
 */

@RestController
@RequestMapping(value = "/blacklist")
public class BlacklistController {
    @Autowired
    BlacklistService blacklistService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "add/{userId}", method = RequestMethod.POST)
    public ResponseEntity addToBlacklist(@RequestBody BlacklistDto blacklistDto, @PathVariable String userId){
        UserDto user = userService.findByUserId(userId);
        if(user!=null){
            blacklistDto.userDto = user;
            BlacklistDto b = blacklistService.persisToBlacklist(blacklistDto);
            return new ResponseEntity(b, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "remove/{userId}", method = RequestMethod.GET)
    public ResponseEntity removeFromBlacklist(@PathVariable String userId){
        blacklistService.removeFromBlacklist(userId);
        return new ResponseEntity(HttpStatus.OK);
        }
    }

