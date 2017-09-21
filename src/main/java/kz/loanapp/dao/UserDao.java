package kz.loanapp.dao;

import kz.loanapp.dto.UserDto;

/**
 * Created by ardak on 9/21/17.
 */
public interface UserDao {

    UserDto findByUserId(String id);
    UserDto persistUser(UserDto user);

}
