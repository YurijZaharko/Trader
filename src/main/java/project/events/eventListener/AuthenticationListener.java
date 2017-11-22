package project.events.eventListener;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationListener {

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event){
        String principal = (String) event.getAuthentication().getPrincipal();

    }
}
