package com.jeasywebframework.domain.dept;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jeasywebframework@gmail.com on 13-12-18.
 */
@Entity()
@Table(name = "sys_dept_user")
public class SysDeptUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 主键ID

    @Column(nullable = false)
    private String username; // 用户名

    @Column(nullable = false)
    private String password; // 密码

    @Column(nullable = false)
    private String salt; // 密码盐

    @Column(nullable = false)
    private Date birthday; // 生日

    @Column(name = "department_id", nullable = false)
    private Long departmentId; // 机构ID

    @Column(nullable = false)
    private String enabled; // 是否有效


    private String address; //  地址


    @Column(nullable = false)
    private Long oorder; // 排序


    private String msn;

    private String qq;

    @Column(nullable = false)
    private String sex; // 性别


    private String postcode; // 邮编

    @Column(nullable = false)
    private String name; // 姓名

    @Column(nullable = false, unique = true)
    private String mobile; // 手机号码

    @Column(name = "home_phone")
    private String homePhone; // 家庭电话

    @Column(name = "office_phone")
    private String officePhone; // 工作电话

    @Column(nullable = false, unique = true)
    private String email; // 邮箱


    @Column(name = "id_card")
    private String idCard; // 身份证号码


    private String descp;//描述


    @Column(name = "create_time", nullable = false)
    private Date createTime; // 创建时间

    @Column(name = "update_time", nullable = false)
    private Date updateTime; // 最后修改时间

    @Column(name = "create_user_id", nullable = false)
    private Long createUserId;

    @Column(name = "update_user_id", nullable = false)
    private Long updateUserId;


    // ========================================================================================
    // ========================================================================================
    // ========================================================================================


    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getOorder() {
        return oorder;
    }

    public void setOorder(Long oorder) {
        this.oorder = oorder;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
