package cr.hacienda.rosal.controller;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    ILoginService loginService;

    @GetMapping("/get-user")
    public ResponseEntity<UserDto> getUser(@RequestParam String userName){
        UserDto userDto;
        try{
            userDto=loginService.getSession(userName);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
