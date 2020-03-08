package com.my.project.entity;

import com.ezhiyang.cloud.commons.mybatis.query.TableColumn;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * 用户表
 * 
 * @author pengjinguo
 * @date 2020-03-08 20:16:23
 */
public class UsersDO implements Serializable {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public enum Column implements TableColumn {
        id("id","id","BIGINT"),
        userName("user_name","userName","VARCHAR"),
        password("password","password","VARCHAR"),
        createDate("create_date","createDate","TIMESTAMP"),
        updateDate("update_date","updateDate","TIMESTAMP");

        private final String column;

        private final String property;

        private final String jdbcType;

        Column(String column, String property, String jdbcType) {
            this.column = column;
            this.property = property;
            this.jdbcType = jdbcType;
        }

        public String getColumn() {
            return this.column;
        }

        public String getProperty() {
            return this.property;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        public Class getModelType() {
            return UsersDO.class;
        }

        public String asc() {
            return this.column + " ASC";
        }

        public String desc() {
            return this.column + " DESC";
        }

    }
}