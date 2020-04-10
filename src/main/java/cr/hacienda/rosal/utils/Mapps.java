package cr.hacienda.rosal.utils;

import java.util.HashMap;
import java.util.Map;

public class Mapps {

    public static Map<Integer, String> getTypeRequest() {
        Map<Integer, String> typeRequest = new HashMap<>();
        typeRequest.put(1, "Queja");
        typeRequest.put(2, "Reclamo");
        typeRequest.put(3, "Peticion");
        return typeRequest;
    }
    static Map<Integer, String> getUserType() {
        Map<Integer, String> userType = new HashMap<>();
        userType.put(1, "Residente");
        userType.put(2, "Administrador");
        return userType;
    }

    public static Map<Integer, String> getSateRequest() {
        Map<Integer, String> stateRequest = new HashMap<>();
        stateRequest.put(1, "Atendido");
        stateRequest.put(2, "Sin respuesta");
        return stateRequest;
    }
}
