package org.taonaw.facilitymanagement.common;

import com.google.gson.Gson;

public class DeepCopy {
    public static <T> T clone(T obj, Class<T> type) {
        var gson = new Gson();
        return gson.fromJson(gson.toJson(obj), type);
    }
}
