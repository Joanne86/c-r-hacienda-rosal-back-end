package cr.hacienda.rosal.controller;

import cr.hacienda.rosal.dto.MessageDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.User;
import cr.hacienda.rosal.service.IDebtorService;
import cr.hacienda.rosal.service.IHomeService;
import cr.hacienda.rosal.service.INotificationService;
import cr.hacienda.rosal.service.IUserService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notification")
public class NotificationController {


    @Autowired
    INotificationService notificationService;
    @Autowired
    IHomeService homeService;
    @Autowired
    IDebtorService debtorService;
    @Autowired
    IUserService userService;

    /**
     * Metodo que agrega todos los numeros del conjunto residencial a aws SNS y los guarda en base de datos
     * @param users lista de residentes
     * @return estado de la peticion
     */

    @PostMapping("/add-all-numbers")
    public ResponseEntity<Void> addNumbersToGeneralMessage(@RequestBody ArrayList<UserDto> users){
        // mirar si hacerlo en hilos
        userService.saveAll(MapperDtos.mapUserDtoToUser(users));

        debtorService.saveAll(MapperDtos.mapUserDtoToDebt(users));

        homeService.saveAll(MapperDtos.mapHomes(users));

        notificationService.addAllNumbers(MapperDtos.mapCellphones(users));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Metodo que agrega los numeros de los deudores del conjunto a SNS
     * @param users lista de residentes morosos
     * @return
     */

    @PostMapping ("/add-debtors-numbers")
    public ResponseEntity<Void> addDebtorsNumbers(@RequestBody  ArrayList<UserDto> users){
        notificationService.addDebtorsNumber(MapperDtos.mapCellphones(users));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Metodo que envia un mensaje igual a todos los residentes del conjunto
     * @param message cuerpo del mensaje
     * @return estado de peticion
     */


    @PostMapping ("/send-message-to-all")
    public ResponseEntity<Void> sendMessageToAll(@RequestBody String message){
        notificationService.sendMessageToAllResidents(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Metodo que envia mensaje personalizado a un residente del conjunto
     * @param messageDto contiene la ifnormacion necesario para enviar un mensaje
     * @return estado de peticion
     */

    @PostMapping ("/send-message-to-one")
    public ResponseEntity<Void> sendMessageToOne(@RequestBody MessageDto messageDto){
        notificationService.sendMessageToOne(messageDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Metodo que envia un mensaje igual a todos los deudores del conjunto
     * @param message
     * @return
     */

    @PostMapping ("/send-message-to-debtors")
    public ResponseEntity<Void> sendMessageToDebtors(@RequestParam String message){
        notificationService.sendMessageToAllDebtors(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * pendiente para que solo traiga los numeris que son
     * @return numeros de celular
     */
    @GetMapping ("/get-all-number")
    public ResponseEntity<Iterable<String>> getAllNumbers(){
        notificationService.getAllNumber();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping ("/get-all-debtors-number")
    public ResponseEntity<Iterable<String>> getAllDebtorsNumbers(){
        notificationService.getAllDebtorsNumber();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete-number")
    public void delete(){
        notificationService.deleteNumber();
    }

}
