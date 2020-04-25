package cr.hacienda.rosal.controller;

import cr.hacienda.rosal.dto.MessageDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.service.*;
import cr.hacienda.rosal.utils.MapperDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    @Autowired
    ILoginService loginService;

    /**
     * Metodo que agrega todos los numeros del conjunto residencial a aws SNS y los guarda en base de datos
     * @param users lista de residentes
     * @return estado de la peticion
     */

    @PostMapping("/add-all-residents")
    public ResponseEntity<Void> addNumbersToGeneralMessage(@RequestBody ArrayList<UserDto> users){
        try{
            userService.saveAll(MapperDtos.mapUserDtoToUser(users));
            debtorService.saveAll(MapperDtos.mapUserDtoToDebt(users));
            homeService.saveAll(MapperDtos.mapHomes(users));
            loginService.saveAll(MapperDtos.mapUserDtosToCredentals(users));
            //notificationService.addAllNumbers(MapperDtos.mapCellphones(users));
            /*String message = "usted acaba de ser registrado en la aplicación web del conjunto residencial ingrese a este link para acceder -> https://conjunto-hacienda-rosal.com/#/login ingresando su cedula en ambos campos";
            notificationService.sendMessageToAllResidents(message);*/
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Metodo que agrega los numeros de los deudores del conjunto a SNS
     * @param users lista de residentes morosos
     * @return
     */

    @PostMapping ("/add-debtors-numbers")
    public ResponseEntity<Void> addDebtorsNumbers(@RequestBody  ArrayList<UserDto> users){
        try{
            //notificationService.addDebtorsNumber(MapperDtos.mapCellphones(users));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que envia un mensaje igual a todos los residentes del conjunto
     * @param message cuerpo del mensaje
     * @return estado de peticion
     */


    @PostMapping ("/send-message-to-all")
    public ResponseEntity<Void> sendMessageToAll(@RequestBody String message){
        try{
            notificationService.sendMessageToAllResidents(message);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Metodo que envia mensaje personalizado a un residente del conjunto
     * @param messageDto contiene la ifnormacion necesario para enviar un mensaje
     * @return estado de peticion
     */

    @PostMapping ("/send-message-to-one")
    public ResponseEntity<Void> sendMessageToOne(@RequestBody MessageDto messageDto){
        try{
            notificationService.sendMessageToOne(messageDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que envia un mensaje igual a todos los deudores del conjunto
     * @param message mensaje que envia a los deudores
     * @return respuesta
     */

    @PostMapping ("/send-message-to-debtors")
    public ResponseEntity<Void> sendMessageToDebtors(@RequestBody String message){
        try{
            notificationService.sendMessageToAllDebtors(message);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * pendiente para que solo traiga los numeris que son
     * @return numeros de celular
     */
    @GetMapping ("/get-all-number")
    public ResponseEntity<Iterable<String>> getAllNumbers(){
        try{
            notificationService.getAllNumber();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping ("/get-all-debtors-number")
    public ResponseEntity<Iterable<String>> getAllDebtorsNumbers(){
        try{
            notificationService.getAllDebtorsNumber();
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Metodo que elimina un numero de telefono en aws
     * @param cellphone telefono
     */
    @DeleteMapping("/delete-number")
    public ResponseEntity<Void> deleteNumber(@RequestParam String cellphone){
        try{
            notificationService.deleteNumber(cellphone);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Metodo que agrega un numero de telefono en aws
     * @param cellphone telefono
     */
    @PostMapping("/add-number")
    public ResponseEntity<Void> addNumber(@RequestParam String cellphone){
        try{
            notificationService.addNumber(cellphone);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    /**
     * Metodo que agrega un numero de telefono de deudor en aws
     * @param cellphone telefono
     */
    @PostMapping("/add-debtor-number")
    public ResponseEntity<Void> addDebtorNumber(@RequestParam String cellphone){
        try{
            notificationService.addDebtorNumber(cellphone);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
