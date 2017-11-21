package project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class VerificationToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private TraderUser traderUser;

    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    private String verificationKey;

    public VerificationToken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TraderUser getTraderUser() {
        return traderUser;
    }

    public void setTraderUser(TraderUser traderUser) {
        this.traderUser = traderUser;
    }

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
