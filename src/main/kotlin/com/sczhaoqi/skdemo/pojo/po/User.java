package com.sczhaoqi.skdemo.pojo.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

/**
 * @author sczhaoqi
 */
@Data
@Entity
@Table(name = "sk_user")
public class User
        extends BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long uid;
    @Column(length = 32,unique = true)
    private String username;
    @Column(length = 256)
    private String password;
    @Column(name = "locked")
    private Boolean locked;
    @Column(name = "expired")
    private Boolean expired;
    @Column(name = "enabled")
    private Boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "rid"))
    private List<Role> roles;

    public User()
    {
    }

    public User(User other){
        this.setUid(other.getUid());
        this.setUsername(other.getUsername());
        this.setPassword(other.getPassword());
        this.setLocked(other.getLocked());
        this.setExpired(other.getExpired());
        this.setEnabled(other.getEnabled());
        this.setRoles(other.getRoles());
    }
}
