package kz.loanapp.dto;

import kz.loanapp.models.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ardak on 9/21/17.
 */
public class UserDto {

    public String id;
    public String name;
    public String surname;

    public static UserDto fromUser(User u){
        UserDto user = new UserDto();
        user.id = u.getPersonalId();
        user.name = u.getName();
        user.surname = u.getSurname();
        return user;
    }

    public static User toUser(UserDto u){
        if(u==null){
            return null;
        }
        else {
            User user = new User();
            user.setPersonalId(u.id);
            user.setName(u.name);
            user.setSurname(u.surname);
            return user;
        }
    }

    public static List<UserDto> fromUsers(List<User> users){
        return users.stream().map(u -> fromUser(u)).collect(Collectors.toList());
    }
}
