package cr.hacienda.rosal.controller;

import cr.hacienda.rosal.dto.DebtDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.service.IDebtorService;
import cr.hacienda.rosal.service.IHomeService;
import cr.hacienda.rosal.service.INotificationService;
import cr.hacienda.rosal.service.IUserService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/resident")
public class ResidentController {

    private Logger logger = LoggerFactory.getLogger(ResidentController.class);

    @Autowired
    IHomeService homeService;
    @Autowired
    IDebtorService debtorService;
    @Autowired
    IUserService userService;

    @Autowired
    INotificationService notificationService;

    @GetMapping("/get-debtors")
    public ResponseEntity<Iterable<UserDto>> getDebtors(){
        return new ResponseEntity<>(debtorService.getAllDebtors(), HttpStatus.OK);
    }

    @PostMapping("/save-resident")
    public ResponseEntity<Void> saveResident(@RequestBody UserDto userDto){
        try{
            userService.save(MapperDtos.getUser(userDto));
            debtorService.save(MapperDtos.getDebt(userDto));
            homeService.save(MapperDtos.getHome(userDto));
            notificationService.addNumber(userDto.getCellphone());
            if(userDto.getDebt()>0 && userDto.getMonths()>0){
                notificationService.addDebtorNumber(userDto.getCellphone());
            }
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e){
            logger.info("Ocurrio un error al guardar residente: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping ("/get-residents")
    public ResponseEntity<Iterable<UserDto>> getResidents(){
        Iterable<UserDto> users = homeService.getAllUsers();
        int size = 0;
        if (users instanceof Collection<?>) {
            size= ((Collection<?>)users).size();
        }
        if(size>0){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping ("/debt-info")
    public ResponseEntity<DebtDto> getDebtInfo(@RequestParam String towerNumberHome){
        DebtDto debtDto;
        try{
            debtDto= debtorService.getDebtInfo(towerNumberHome);
            return new ResponseEntity<>(debtDto,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping ("/update")
    public ResponseEntity<Void> updateResident(@RequestBody UserDto userDto){
        try{
            userService.update(MapperDtos.getUser(userDto));
            debtorService.update(MapperDtos.getDebt(userDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            logger.info("Ocurrio un error al actualizar residente: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
