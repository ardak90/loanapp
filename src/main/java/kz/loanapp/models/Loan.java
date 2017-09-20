package kz.loanapp.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by ardak on 9/21/17.
 */

@Entity
@Table(name="app_loan")
public class Loan {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="ID_", nullable = false)
        private Long id;

        @Column(name="DATE_APPLIED_", nullable = false)
        private Date dateApplied;


        @Column(name="TERM_DATE_", nullable = false)
        private Date termDate;

        @Column(name="AMOUNT_", nullable = false)
        private BigDecimal amount;

        @Column(name="COUNTRY_APPLIED_FROM_", nullable = false)
        private String countryFrom;

        @ManyToOne
        @JoinColumn(name="USER_")
        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getDateApplied() {
            return dateApplied;
        }

        public void setDateApplied(Date dateApplied) {
            this.dateApplied = dateApplied;
        }

        public Date getTermDate() {
            return termDate;
        }

        public void setTermDate(Date termDate) {
            this.termDate = termDate;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getCountryFrom() {
            return countryFrom;
        }

        public void setCountryFrom(String countryFrom) {
            this.countryFrom = countryFrom;
        }
}
