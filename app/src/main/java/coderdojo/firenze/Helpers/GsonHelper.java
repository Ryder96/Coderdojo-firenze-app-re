package coderdojo.firenze.Helpers;

import com.google.gson.Gson;


public class GsonHelper {

    private static Gson _gson;

    public static Gson getGsonInstance() {
        if (_gson == null)
            _gson = new Gson();
        return _gson;
    }

    public static Object get(String json, Class classe) {
        return getGsonInstance().fromJson(json, classe);
    }

    public static String convert(Object object) {
        return getGsonInstance().toJson(object);
    }

}
