package com.innovateEDU.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "com.innovateEDU.core.User.findAll",
                query = "select u from User u"),
        @NamedQuery(name = "com.innovateEDU.core.User.findByUsername",
                query = "select u from User u where u.username = :username and u.password = :password")

})
public class User implements Principal{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    public User() {
    }

    public User(String username, String password) {

        this.username = username;
        this.password  = password;

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

}
