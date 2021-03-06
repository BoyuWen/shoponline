package com.shoponline.domain;

import java.io.Serializable;


public class OrderPrimary implements Serializable {
    private int userId;
    private int productId;
    private String time;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OrderPrimary)) return false;

        OrderPrimary that = (OrderPrimary) object;

        if (getUserId() != that.getUserId()) return false;
        if (getProductId() != that.getProductId()) return false;
        return getTime().equals(that.getTime());
    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + getProductId();
        result = 31 * result + getTime().hashCode();
        return result;
    }
}
