package kz.loanapp.dto;

import kz.loanapp.models.Blacklist;
import kz.loanapp.models.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ardak on 9/22/17.
 */
public class BlacklistDto {

    public Long id;
    public UserDto userDto;
    public String description;

    public static BlacklistDto fromBlacklist(Blacklist blacklist){
        BlacklistDto b = new BlacklistDto();
        b.id = blacklist.getId();
        b.description = blacklist.getDescription();
        b.userDto = UserDto.fromUser(blacklist.getUser());
        return b;
    }

    public static Blacklist toBlacklist(BlacklistDto blacklist){
        if(blacklist==null){
            return null;
        }
        else {
            Blacklist l = new Blacklist();
            l.setId(blacklist.id);
            l.setDescription(blacklist.description);
            l.setUser(UserDto.toUser(blacklist.userDto));
            return l;
        }
    }

    public static List<BlacklistDto> fromBlacklists(List<Blacklist> blacklists){
        return blacklists.stream().map(blacklist -> fromBlacklist(blacklist)).collect(Collectors.toList());

    }
}
