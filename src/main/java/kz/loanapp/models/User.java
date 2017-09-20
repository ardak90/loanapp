package kz.loanapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by ardak on 9/21/17.
 */
@Entity
@Table(name="app_user")
public class User implements Serializable {

    @NotNull
    @Id
    @Pattern(regexp = "^[a-z0-9\\.\\-\\_]*$")
    @Size(min = 1, max = 50)
    @Column(name="PERSONAL_ID",length = 50, unique = true, nullable = false)
    private String personalId;

    @Column(name="NAME_", nullable = false)
    private String name;

    @Column(name="SURNAME_", nullable = false)
    private String surname;

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
