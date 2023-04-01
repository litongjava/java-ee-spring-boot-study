package com.litong.study.spring.boot.mybatis.plus.one.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 普通用户
 * </p>
 *
 * @author litong
 * @since 2020-04-24
 */
@TableName("user_normal")
public class UserNormal extends Model<UserNormal> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @TableField("user_name")
    private String userName;

    @TableField("user_age")
    private Integer userAge;

    private Date birthday;

    @TableField("phone_num")
    private String phoneNum;

    private String address;

    private String remark;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "UserNormal{" +
        "userId=" + userId +
        ", userName=" + userName +
        ", userAge=" + userAge +
        ", birthday=" + birthday +
        ", phoneNum=" + phoneNum +
        ", address=" + address +
        ", remark=" + remark +
        "}";
    }
}
