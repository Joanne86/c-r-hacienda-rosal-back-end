package cr.hacienda.rosal.controller;

import cr.hacienda.rosal.entities.Home;
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
@RequestMapping("/home")
public class HomeController {

    @Autowired
    IHomeService homeService;

    @GetMapping("/get-homes")
    public ResponseEntity<Iterable<Home>> getAllHomes(){
        Iterable<Home> homes = homeService.getAllHomes();
        return new ResponseEntity<>(homes, HttpStatus.OK);
    }

}
