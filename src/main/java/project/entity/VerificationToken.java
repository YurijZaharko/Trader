package project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class VerificationToken implements Serializable {
    private Long id;
    private TraderUser traderUser;
    private Date expirationDate;
    private String verificationKey;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public TraderUser getTraderUser() {
        return traderUser;
    }

    public void setTraderUser(TraderUser traderUser) {
        this.traderUser = traderUser;
    }

    @Temporal(TemporalType.DATE)
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getVerificationKey() {
        return verificationKey;
    }

    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
    }
}
