package com.example.hoanghiep.projectcakemaker.model;

/**
 * Created by HoangHiep on 12/13/15.
 */
public class Screen {
    public static final int HOME = 0;
    public static final int CATEGORY = 1;
    public static final int EVENT = 2;
    public static final int ABOUT_US = 3;
    public static final int CONTACT_US = 4;
    public static final int REGISTER_LOGIN = 5;

    public String title;
    public int icon_res;
    public boolean is_selected;

    public Screen(int icon_res, String title) {
        this.icon_res = icon_res;
        this.title = title;
    }

    public Screen(int icon_res, boolean is_selected, String title) {
        this.icon_res = icon_res;
        this.is_selected = is_selected;
        this.title = title;
    }

}
