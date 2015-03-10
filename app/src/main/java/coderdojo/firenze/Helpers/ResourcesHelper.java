package coderdojo.firenze.Helpers;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;


public class ResourcesHelper {
    public static BitmapDescriptor getBitmapFormRes(Context context, int res) {
        return BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(context.getResources(), res));
    }
}
