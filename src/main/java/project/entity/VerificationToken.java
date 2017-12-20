package project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
public class VerificationToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private TraderUser traderUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar expirationDate;
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

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getVerificationKey() {
        return verificationKey;
    }

    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VerificationToken)) return false;

        VerificationToken that = (VerificationToken) o;

        return traderUser != null ? traderUser.equals(that.traderUser) : that.traderUser == null;
    }

    @Override
    public int hashCode() {
        return traderUser != null ? traderUser.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", traderUser=" + traderUser +
                '}';
    }
}
