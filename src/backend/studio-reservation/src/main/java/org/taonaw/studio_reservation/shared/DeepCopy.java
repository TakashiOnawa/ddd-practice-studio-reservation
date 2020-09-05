package org.taonaw.studio_reservation.shared;

import com.google.gson.GsonBuilder;

public class DeepCopy {
    public static <T> T clone(T obj, Class<T> type) {
        var gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.fromJson(gson.toJson(obj), type);
    }
}
