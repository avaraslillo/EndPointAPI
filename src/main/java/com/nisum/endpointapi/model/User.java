package com.nisum.endpointapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "usuario")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
	private UUID uuid;
    @Column(name = "name")
	private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created")
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified")
    private Date modified;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login")
    private Date last_login;
    @Column(name="token")
    private String token;
    @Column(name="is_active")
    private boolean is_active;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Telephone> phone_list = new ArrayList<Telephone>();

    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }
    public Date getLast_login() {
        return last_login;
    }
    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public boolean isIs_active() {
        return is_active;
    }
    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Telephone> getPhone_list() {
        return phone_list;
    }
    public void setPhone_list(List<Telephone> phone_list) {
        this.phone_list = phone_list;
    }

    public User(){

    }

    public User(UUID uuid, String name, String email, String password, List<Telephone> phone_list, Date created,
            Date modified, Date last_login, String token, boolean is_active) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_list = phone_list;
        this.created = created;
        this.modified = modified;
        this.last_login = last_login;
        this.token = token;
        this.is_active = is_active;
    }

    public User(String name, String email, String password, List<Telephone> phone_list, Date created,
    Date modified, Date last_login, String token, boolean is_active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_list = phone_list;
        this.created = created;
        this.modified = modified;
        this.last_login = last_login;
        this.token = token;
        this.is_active = is_active;
    }
    @Override
    public String toString() {
        return "User [uuid=" + uuid + ", name=" + name + ", email=" + email + ", password=" + password + ", created="
                + created + ", modified=" + modified + ", last_login=" + last_login + ", token=" + token
                + ", is_active=" + is_active + "]";
    }


    


    


}
