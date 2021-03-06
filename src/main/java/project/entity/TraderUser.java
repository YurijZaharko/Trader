package project.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.entity.enumtype.Role;

import javax.persistence.*;
import java.util.*;

@Entity
public class TraderUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "traderUser")
    private VerificationToken verificationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar registrationTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar firstTimeOfWrongLogin;
    private String username;
    private String password;
    private String nickName;
    private boolean accountNonLocked = true;
    private boolean enabled = false;
    private int failAttempts;

    public TraderUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.toString());
        grantedAuthorityList.add(simpleGrantedAuthority);
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
    }

    public Calendar getFirstTimeOfWrongLogin() {
        return firstTimeOfWrongLogin;
    }

    public void setFirstTimeOfWrongLogin(Calendar firstTimeOfWrongLogin) {
        this.firstTimeOfWrongLogin = firstTimeOfWrongLogin;
    }

    public int getFailAttempts() {
        return failAttempts;
    }

    public void setFailAttempts(int failAttempts) {
        this.failAttempts = failAttempts;
    }

    public Calendar getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Calendar registration) {
        this.registrationTime = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TraderUser)) return false;
        TraderUser that = (TraderUser) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "TraderUser{" +
                "id=" + id +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
