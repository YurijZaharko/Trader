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
        TraderUser tradeUser = traderUserService.findByUsername(userName);
        int failAttempts = tradeUser.getFailAttempts();
        if (userFailAgain(tradeUser)) {
            if (failAttempts >= MAX_NUMBER_OF_LOGIN_FAILS) {
                tradeUser.setAccountNonLocked(false);
            } else {
                tradeUser.setFailAttempts(++failAttempts);
            }
        } else {
           setFirstFailLogin(tradeUser);
        }
        traderUserRepository.save(tradeUser);
    }

    private void setFirstFailLogin(TraderUser traderUser){
        int firstFailAttempts = 1;
        traderUser.setFailAttempts(firstFailAttempts);
        traderUser.setFirstTimeOfWrongLogin(Calendar.getInstance());
    }

    private boolean userFailAgain(TraderUser tradeUser) {
        Calendar failLoginTime = tradeUser.getFirstTimeOfWrongLogin();
        return failLoginTime != null && failLoginTime.after(getTimeOneHourBefore());
    }

    private Calendar getTimeOneHourBefore() {
        int hours = -1;
        Calendar onrHourBefore = Calendar.getInstance();
        onrHourBefore.add(Calendar.HOUR_OF_DAY, hours);
        return onrHourBefore;
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
