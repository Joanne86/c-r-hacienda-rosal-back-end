package cr.hacienda.rosal.controller;

import cr.hacienda.rosal.dto.DebtDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.service.IDebtorService;
import cr.hacienda.rosal.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/resident")
public class ResidentController {

    @Autowired
    IHomeService homeService;
    @Autowired
    IDebtorService debtorService;

    @GetMapping("/get-debtors")
    public ResponseEntity<Iterable<UserDto>> getDebtors(){
        return new ResponseEntity<>(debtorService.getAllDebtors(), HttpStatus.OK);
    }

    /*GetMapping("/get-debtor-info")
    public ResponseEntity<DebtDto> getDebtorInfo(){
        return new ResponseEntity<>(debtorService.get(), HttpStatus.OK);
    }*/
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
}
