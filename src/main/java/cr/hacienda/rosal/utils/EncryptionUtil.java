package cr.hacienda.rosal.utils;

import java.util.Base64;

public class EncryptionUtil {

    public static String decode (String param){
        byte[] bytesDecodificados = Base64.getDecoder().decode(param);
        return new String(bytesDecodificados);
    }
}
