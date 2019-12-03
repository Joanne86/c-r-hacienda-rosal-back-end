package cr.hacienda.rosal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/resident")
public class ResidentController {

    @GetMapping("/debtors")
    public ResponseEntity<Void> getDebtors(){
        return null;
    }
}
