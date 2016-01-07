package com.example.hoanghiep.projectcakemaker.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Feedback")
public class Feedback extends ParseObject{
    public Feedback() {
    }

    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        put("name", name);
    }

    public String getEmail()
    {
        return getString("email");
    }

    public void setEmail(String email)
    {
        put("email", email);
    }

    public String getPhone()
    {
        return getString("phone");
    }

    public void setPhone(String phone)
    {
        put("phone", phone);
    }

    public String getSubject()
    {
        return getString("subject");
    }

    public void setSubject(String subject)
    {
        put("subject", subject);
    }

    public String getMessage()
    {
        return getString("message");
    }

    public void setMessage(String message)
    {
        put("message", message);
    }


}
