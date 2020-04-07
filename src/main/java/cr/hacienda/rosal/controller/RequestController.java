package cr.hacienda.rosal.controller;


import cr.hacienda.rosal.dto.RequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/request")
public class RequestController {

    @PostMapping("/send-request")
    public ResponseEntity<Void> sendRequest(@RequestBody RequestDto requestDto){

    }
}
