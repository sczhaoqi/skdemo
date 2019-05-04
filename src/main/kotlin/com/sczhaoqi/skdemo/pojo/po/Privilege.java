package com.sczhaoqi.skdemo.pojo.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sczhaoqi
 * @date 2019/5/4 1:49
 */
@Data
@Entity
@Table(name = "sk_privilege")
public class Privilege
        extends BaseEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long pid;
    @Column(name = "privilege_name", length = 32, unique = true)
    private String privilegeName;
}
