package project.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.lang.Factory;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.mockito.junit.MockitoJUnitRunner;
import project.configuration.MeanBeanConfiguration;

import java.util.Calendar;

@RunWith(MockitoJUnitRunner.class)
public class VerificationTokenTest extends MeanBeanConfiguration {
    private Configuration configuration;

    @Before
    public void setUp() throws Exception {
        this.configuration = new ConfigurationBuilder()
                .overrideFactory("traderUser", new TraderUserFactory())
                .overrideFactory("expirationDate", new CalendarFactory())
                .build();
    }

    @Test
    public void gettersAndSettersCorrectness() throws Exception {
        beanTester.testBean(VerificationToken.class, this.configuration);
    }

    @Test
    public void equalsAndHashCorrectness() throws Exception {
        TraderUser traderUser = new TraderUser();
        traderUser.setUsername("testUserName");

        VerificationToken verificationTokenBlack = new VerificationToken();
        verificationTokenBlack.setTraderUser(traderUser);

        VerificationToken verificationTokenRed = new VerificationToken();
        verificationTokenRed.setTraderUser(traderUser);

        Assert.assertTrue(verificationTokenBlack.equals(verificationTokenRed));
        Assert.assertTrue(verificationTokenBlack.hashCode() == verificationTokenRed.hashCode());
    }

    class TraderUserFactory implements Factory<TraderUser>{

        @Override
        public TraderUser create() {
            return new TraderUser();
        }
    }

    class CalendarFactory implements Factory<Calendar>{

        @Override
        public Calendar create() {
            return Calendar.getInstance();
        }
    }

}