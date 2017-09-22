package kz.loanapp.dao;

import kz.loanapp.dto.BlacklistDto;

/**
 * Created by ardak on 9/22/17.
 */
public interface BlacklistDao {

    BlacklistDto persisToBlacklist(BlacklistDto blacklist);
    void removeFromBlacklist(String userId);
    boolean isInBlacklist(String userId);

}
