package loaiza.andres.it.coderdojo_firenze.Helpers;

import com.google.gson.Gson;

/**
 * Created by Garu on 28/12/2014.
 */
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
