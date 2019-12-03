package cr.hacienda.rosal.controller;

import cr.hacienda.rosal.entities.ResidentCredentials;
import cr.hacienda.rosal.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notification")
public class NotificationController {


    @Autowired
    INotificationService notificationService;

    /**
     * Metodo que agrega todos los numeros del conjunto residencial a aws SNS
     * @param residentCredentialsList lista de residentes
     * @return
     */


    @PostMapping("/add-all-numbers")
    public ResponseEntity<Void> addNumbersToGeneralMessage(@RequestBody List<ResidentCredentials> residentCredentialsList){
        //poner try
        //notificationService.save(residentCredentialsList);
        notificationService.addAllNumbers(residentCredentialsList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Metodo que agrega los numeros de los deudores del conjunto a SNS
     * @param residentCredentialsList lista de residentes morosos
     * @return
     */

    @PostMapping ("/add-debtors-numbers")
    public ResponseEntity<Void> addDebtorsNumbers(@RequestBody List<ResidentCredentials> residentCredentialsList){
        //recibir una lista de objetos con:
        //guardar en base de datos con los nombres y numero de casa
        //publicar en sns(Deudores) cada numero
        return null;
    }

    /**
     * Metodo que envia un mensaje igual a todos los residentes del conjunto
     * @param message cuerpo del mensaje
     * @return
     */


    @PostMapping ("/send-message-to-all")
    public ResponseEntity<Void> sendMessageToAll(@RequestParam String message){
        //se tiene que tener los numeros ya montados en el topic
        return null;
    }

    /**
     * Metodo que envia un mensaje igual a todos los deudores del conjunto
     * @param message
     * @return
     */

    @PostMapping ("/send-message-to-debtors")
    public ResponseEntity<Void> sendMessageToDebtors(@RequestParam String message){
        //se tiene que tener los numeros ya montados en el topic
        return null;
    }

    /**
     * Metodo que envia mensaje personalizado a un moroso del conjunto
     * @param message
     * @return
     */


    @PostMapping ("/send-message-to-one")
    public ResponseEntity<Void> sendMessageToOne(@RequestParam  String message, @RequestBody ResidentCredentials residentCredentials){
        //buscar como hacer esta logica, enviar a un numero de telefono teniendo el topic ya creado
        return null;
    }
}
