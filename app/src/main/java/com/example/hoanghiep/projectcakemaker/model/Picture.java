package com.example.hoanghiep.projectcakemaker.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Picture")
public class Picture extends ParseObject {
    public Picture() {
    }

    //Name
    public String getPictureDescription()
    {
        return getString("Description");
    }

    public void setPictureDescription(String value)
    {
        put("Description", value);
    }

    //get/set file
    public ParseFile getFile()
    {
        return getParseFile("fileName");
    }

    public void setFile(ParseFile parseFile)
    {
        put("fileName", parseFile);
    }

}
