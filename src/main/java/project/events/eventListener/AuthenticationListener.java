package project.events.eventListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import project.service.AuthenticationFailedCounterService;

@Component
public class AuthenticationListener {
    private AuthenticationFailedCounterService authenticationFailedCounterService;

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event){
        Throwable cause = event.getException();
        if (!(cause instanceof UsernameNotFoundException)){
            Authentication authentication = event.getAuthentication();
            String userName = (String) authentication.getPrincipal();
            authenticationFailedCounterService.checkUser(userName);
        }


    }

    @Autowired
    public void setAuthenticationFailedCounterService(AuthenticationFailedCounterService authenticationFailedCounterService) {
        this.authenticationFailedCounterService = authenticationFailedCounterService;
    }
}
