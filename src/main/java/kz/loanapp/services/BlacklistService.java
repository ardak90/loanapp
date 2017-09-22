package kz.loanapp.services;

import kz.loanapp.dao.BlacklistDao;
import kz.loanapp.dto.BlacklistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ardak on 9/22/17.
 */
@Transactional
@Service
public class BlacklistService {

    @Autowired
    BlacklistDao blacklistDao;

    public BlacklistDto persisToBlacklist(BlacklistDto blacklist){
        return blacklistDao.persisToBlacklist(blacklist);
    };

    public void removeFromBlacklist(String userId){
        blacklistDao.removeFromBlacklist(userId);
    };

    public boolean isInBlacklist(String userId){
        return blacklistDao.isInBlacklist(userId);
    };

}
