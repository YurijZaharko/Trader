package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.TraderUser;
import project.repository.TraderUserRepository;
import project.service.AuthenticationFailedCounterService;
import project.service.TraderUserService;

import java.util.Calendar;

@Service
public class AuthenticationFailedCounterImpl implements AuthenticationFailedCounterService {
    private TraderUserService traderUserService;
    private TraderUserRepository traderUserRepository;
    private static final int MAX_NUMBER_OF_LOGIN_FAILS = 5;

    @Override
    public void checkUser(final String userName) {
        int hours = -1;
        TraderUser tradeUser = traderUserService.findByUsername(userName);
        Calendar onrHourBefore = Calendar.getInstance();
        onrHourBefore.add(Calendar.HOUR_OF_DAY, hours);

        Calendar failLoginTime = tradeUser.getFirstTimeOfWrongLogin();
        int failAttempts = tradeUser.getFailAttempts();
        int firstFailAttempts = 1;
        if (failLoginTime != null && failLoginTime.after(onrHourBefore)){
            if (failAttempts >= MAX_NUMBER_OF_LOGIN_FAILS){
                tradeUser.setAccountNonLocked(false);
            } else {
                failAttempts++;
                tradeUser.setFailAttempts(failAttempts);
            }
        } else {
            tradeUser.setFailAttempts(firstFailAttempts);
            tradeUser.setFirstTimeOfWrongLogin(Calendar.getInstance());
        }
        traderUserRepository.save(tradeUser);
    }

    @Autowired
    public void setTraderUserService(TraderUserService traderUserService) {
        this.traderUserService = traderUserService;
    }

    @Autowired
    public void setTraderUserRepository(TraderUserRepository traderUserRepository) {
        this.traderUserRepository = traderUserRepository;
    }
}
