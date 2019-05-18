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

@Data
@Entity
@Table(name = "sk_role")
public class Role
        extends BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long rid;
    @Column(name = "role_name", length = 32, unique = true)
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "rid"),
            inverseJoinColumns = @JoinColumn(name = "pid"))
    private List<Privilege> privileges;
}
