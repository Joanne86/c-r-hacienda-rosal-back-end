package cr.hacienda.rosal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class healthController {
    @GetMapping("/health")
    public ResponseEntity health() {
        return ResponseEntity.ok("ok ..");
    }
}
