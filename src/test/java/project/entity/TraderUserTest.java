package project.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.meanbean.lang.Factory;
import org.meanbean.test.ConfigurationBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import project.configuration.MeanBeanConfiguration;
import project.entity.enumtype.Role;

import java.util.Calendar;
import java.util.Collection;

public class TraderUserTest extends MeanBeanConfiguration {
    private org.meanbean.test.Configuration configuration;

    private TraderUser unit;

    @Before
    public void setUp() throws Exception {
        this.unit = new TraderUser();

        this.configuration = new ConfigurationBuilder()
                .iterations(10)
                .overrideFactory("registrationTime", new CalendarFactory())
                .overrideFactory("firstTimeOfWrongLogin", new CalendarFactory())
                .overrideFactory("verificationToken", new VerificationTokenFactory())
                .build();
    }

    @Test
    public void gettersAndSettersCorrectness(){
        beanTester.testBean(TraderUser.class, this.configuration);
    }

    @Test
    public void equalsAndHashCorrectness(){
        String sameName = "testName";

        TraderUser traderUserBlack = new TraderUser();
        traderUserBlack.setUsername(sameName);

        TraderUser traderUserRed = new TraderUser();
        traderUserRed.setUsername(sameName);

        Assert.assertTrue(traderUserBlack.equals(traderUserRed));
        Assert.assertTrue(traderUserBlack.hashCode() == traderUserRed.hashCode());
    }

    @Test
    public void grantedAuthoritiesTest() throws Exception {
        //given
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(Role.ROLE_REGISTERED_USER.toString());
        TraderUser traderUser = new TraderUser();
        traderUser.setRole(Role.ROLE_REGISTERED_USER);
        //when
        Collection<? extends GrantedAuthority> authorities = traderUser.getAuthorities();
        //then
        Assert.assertTrue(authorities.contains(simpleGrantedAuthority));
    }

    @Test
    public void isAccountNonExpiredTest() throws Exception {
        Assert.assertTrue(unit.isAccountNonExpired());
    }

    @Test
    public void isCredentialsNonExpiredTest() throws Exception {
        Assert.assertTrue(unit.isCredentialsNonExpired());
    }

    class CalendarFactory implements Factory<Calendar>{

        @Override
        public Calendar create() {
            return Calendar.getInstance();
        }
    }

    class VerificationTokenFactory implements Factory<VerificationToken>{

        @Override
        public VerificationToken create() {
            return new VerificationToken();
        }
    }
}