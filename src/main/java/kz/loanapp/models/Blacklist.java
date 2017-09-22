package kz.loanapp.models;

import javax.persistence.*;

/**
 * Created by ardak on 9/22/17.
 */
@Entity
@Table(name="app_blacklist")
public class Blacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name="USER_", unique = true, nullable = false)
    private User user;

    @Column(name="DESCRIPTION_")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
