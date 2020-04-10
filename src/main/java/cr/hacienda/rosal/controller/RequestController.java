package cr.hacienda.rosal.controller;

import cr.hacienda.rosal.dto.RequestDto;
import cr.hacienda.rosal.service.IRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/request")
public class RequestController {
    private Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    IRequestService requestService;

    @PostMapping("/send-request")
    public ResponseEntity<Void> sendRequest(@RequestBody RequestDto requestDto){
        try{
            logger.info("requestDto: {}", requestDto.toString());
            requestService.sendRequest(requestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<Iterable<RequestDto>> getAllRequest(){
        try{
            Iterable<RequestDto> requestDtos = requestService.getAllRequest();
            return new ResponseEntity<>(requestDtos,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-responses")
    public ResponseEntity<Iterable<RequestDto>> getResponses(@RequestParam String towerNumberHome){
        try{
            Iterable<RequestDto> requestDtos = requestService.findAllByTowerNumberHome(towerNumberHome);
            return new ResponseEntity<>(requestDtos,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @PutMapping("/update-response")
    public ResponseEntity<Void> updateResponse(@RequestBody RequestDto requestDto ){
       try{
           requestService.updateResponse(requestDto);
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
