package stis.kelompok4.drstis;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by Mahendri on 20/06/2017.
 */

public class JWTUtils {

    public static String payloadJsonString(String jwt){
        String[] split = jwt.split("\\.");
        String payload = split[1];
        byte[] decodedPayload = Base64.decode(payload, Base64.URL_SAFE);

        try {
            return new String(decodedPayload, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
