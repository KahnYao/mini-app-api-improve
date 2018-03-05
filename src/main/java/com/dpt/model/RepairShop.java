package com.dpt.model;


import java.util.Date;

public class RepairShop{
    private Integer id;

    private String name;

    private String contact;

    private String mobile;

    private String Email;

    private Integer province;

    private Integer city;

    private Integer distict;

    private String address;

    private String licenceImg;

    private String position;

    private String tjperson;

    private Integer isrz;

    private String mendianHead;

    private Date createDate;

    private Date  modifyDate;

    private String provinceName;

    private String cityName;

    private String distictName;

    private Integer source;

    private double amount;

    private String code;

    private String mendianView;

    private Integer channel;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public void setDistict(Integer distict) {
        this.distict = distict;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLicenceImg(String licenceImg) {
        this.licenceImg = licenceImg;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setIsrz(Integer isrz) {
        this.isrz = isrz;
    }

    public void setMendianHead(String mendianHead) {
        this.mendianHead = mendianHead;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return Email;
    }

    public Integer getProvince() {
        return province;
    }

    public Integer getCity() {
        return city;
    }

    public Integer getDistict() {
        return distict;
    }

    public String getAddress() {
        return address;
    }

    public String getLicenceImg() {
        return licenceImg;
    }

    public String getPosition() {
        return position;
    }

    public Integer getIsrz() {
        return isrz;
    }

    public String getMendianHead() {
        return mendianHead;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) { this.modifyDate = modifyDate; }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistictName() {
        return distictName;
    }

    public void setDistictName(String distictName) {
        this.distictName = distictName;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTjperson() {
        return tjperson;
    }

    public void setTjperson(String tjperson) {
        this.tjperson = tjperson;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMendianView() {
        return mendianView;
    }

    public void setMendianView(String mendianView) {
        this.mendianView = mendianView;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }
}