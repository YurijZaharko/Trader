package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.entity.TraderUser;
import project.entity.enumtype.Role;
import project.service.CustomAuthenticationProvider;
import project.service.TraderUserService;

import java.util.LinkedList;
import java.util.List;

@Service
public class CustomAuthenticationProviderImpl implements CustomAuthenticationProvider {
    private TraderUserService traderUserService;
    private BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = authentication.getPrincipal() + "";
        String credential = authentication.getCredentials() + "";
        TraderUser user = traderUserService.findByUsername(principal);

        List<GrantedAuthority> grantedAuthorityList = getAuthorityList(user);
        isAccountDisable(user);
        isBadCredential(user, credential);

        return new UsernamePasswordAuthenticationToken(user, credential, grantedAuthorityList);
    }

    private List<GrantedAuthority> getAuthorityList(TraderUser user){
        Role role = user.getRole();
        List<GrantedAuthority> grantedAuthorityList = new LinkedList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(role.toString()));
        return grantedAuthorityList;
    }

    private void isAccountDisable(TraderUser user){
        if (!user.isAccountNonLocked()){
            throw new LockedException("Account is locked");
        }
    }

    private void isBadCredential(TraderUser user, String credential){
        String password = user.getPassword();
        if (!encoder.matches(credential, password)){
            throw new BadCredentialsException("Bad Credential");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Autowired
    public void setTraderUserService(TraderUserService traderUserService) {
        this.traderUserService = traderUserService;
    }

    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
