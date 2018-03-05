package com.dpt.model;

public class Order {
    private Integer id;

    private String openId;

    private Long payEndTime;

    private Long prepayEndTime;

    private String prepayId;

    private Integer productId;

    private Double total;

    private Long createdTime;

    private Long updatedTime;

    private Integer payStatus;

    private Integer verifyStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Long payEndTime) {
        this.payEndTime = payEndTime;
    }

    public Long getPrepayEndTime() {
        return prepayEndTime;
    }

    public void setPrepayEndTime(Long prepayEndTime) {
        this.prepayEndTime = prepayEndTime;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId == null ? null : prepayId.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
}