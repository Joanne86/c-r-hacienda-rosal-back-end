package cr.hacienda.rosal.controller;

import cr.hacienda.rosal.dto.CredentialDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.service.ILoginService;
import javassist.NotFoundException;
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
    public ResponseEntity<UserDto> getUser(@RequestParam String user, String password){
        UserDto userDto;
        try{
            CredentialDto credentialDto = new CredentialDto();
            credentialDto.setUser(user);
            credentialDto.setPassword(password);
            userDto=loginService.getSession(credentialDto);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
