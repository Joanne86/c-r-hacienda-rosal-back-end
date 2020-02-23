package cr.hacienda.rosal.dto;

/**
 * Clase dto con informacion necesario para enviar un mensaje de texto a solo un numero de telefono
 */
public class MessageDto {
    private String phoneNumber;
    private String message;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
