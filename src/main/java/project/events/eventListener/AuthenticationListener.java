package project.events.eventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import project.service.AuthenticationFailedCounterService;

@Component
public class AuthenticationListener {
    private AuthenticationFailedCounterService authenticationFailedCounterService;

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event){
        String userName = (String) event.getAuthentication().getPrincipal();
        authenticationFailedCounterService.checkUser(userName);
    }

    @Autowired
    public void setAuthenticationFailedCounterService(AuthenticationFailedCounterService authenticationFailedCounterService) {
        this.authenticationFailedCounterService = authenticationFailedCounterService;
    }
}
