package loaiza.andres.it.coderdojo_firenze.Helpers;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/**
 * Created by Garu on 05/01/2015.
 */
public class ResourcesHelper {
    public static BitmapDescriptor getBitmapFormRes(Context context, int res) {
        return BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(context.getResources(), res));
    }
}
