package com.example.hoanghiep.projectcakemaker.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;

@ParseClassName("Order")
public class Order extends ParseObject {
    public Order() {
    }

    //Name
    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    //Address
    public String getAddress() {
        return getString("address");
    }

    public void setAddress(String address) {
        put("address", address);
    }

    //Phone
    public String getPhone() {
        return getString("phone");
    }

    public void setPhone(String phone) {
        put("phone", phone);
    }

    //Email
    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        put("email", email);
    }

    //deliveryDate
    public String getDeliveryDate() {
        return getString("deliveryDate");
    }

    public void setDeliveryDate(String date) {
        put("deliveryDate", date);
    }

    //Total
    public Double getTotal()
    {
        return getDouble("total");
    }

    public void setTotal(Double total)
    {
        put("total", total);
    }

}
