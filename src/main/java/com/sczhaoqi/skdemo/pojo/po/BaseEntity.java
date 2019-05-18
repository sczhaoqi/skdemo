package com.sczhaoqi.skdemo.pojo.po;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import java.sql.Timestamp;

/**
 * @author sczhaoqi
 * @date 2019/5/1 22:38
 */
@MappedSuperclass
public class BaseEntity
{
    @Column(name = "create_time",columnDefinition="timestamp default current_timestamp comment '创建时间'")
    private Timestamp createTime;
    @Column(name = "update_time",columnDefinition="timestamp default current_timestamp comment '更新时间'")
    private Timestamp updateTime;
    @Column(name = "create_user")
    private Long createUser;
    @Column(name = "update_user")
    private Long updateUser;

    public Timestamp getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime)
    {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime)
    {
        this.updateTime = updateTime;
    }

    public Long getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(Long createUser)
    {
        this.createUser = createUser;
    }

    public Long getUpdateUser()
    {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser)
    {
        this.updateUser = updateUser;
    }
}
