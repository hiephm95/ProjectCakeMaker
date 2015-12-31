package com.example.hoanghiep.projectcakemaker.Parse;

import com.example.hoanghiep.projectcakemaker.model.Event;
import com.example.hoanghiep.projectcakemaker.model.Order;
import com.example.hoanghiep.projectcakemaker.model.Picture;
import com.example.hoanghiep.projectcakemaker.model.Product;
import com.parse.Parse;
import com.parse.ParseObject;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Product.class);
        ParseObject.registerSubclass(Event.class);
        ParseObject.registerSubclass(Picture.class);
        ParseObject.registerSubclass(Order.class);
        Parse.initialize(this, "l5OJy4F4rw3COKG6Jgc0VKNi7rFQzarUVLcjw4jA", "HCRpx0LQxTlvaBXDQ6BxeFsLnJqkGscA9xf1aq8Q");
    }
}
