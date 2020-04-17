package cr.hacienda.rosal.controller;

import cr.hacienda.rosal.dto.CommentaryDto;
import cr.hacienda.rosal.dto.NewsDto;
import cr.hacienda.rosal.entities.News;
import cr.hacienda.rosal.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/publish")
public class NewController {

    @Autowired
    INewService newService;

    @PostMapping("/save-new")
    public ResponseEntity<News> publish(@RequestBody News new_){
        try{
            newService.publish(new_);
            return new ResponseEntity<>(new_,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get-news")
    public ResponseEntity<Iterable<NewsDto>> getNews(){
        try{
            Iterable<NewsDto> news = newService.getNews();
            return new ResponseEntity<>(news, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save-commentary")
    public ResponseEntity<Void> saveCommentary(@RequestBody CommentaryDto commentaryDto){
        try{
            newService.saveCommentary(commentaryDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/get-commentaries")
    public ResponseEntity<Iterable<CommentaryDto>> getCommentaries(@RequestParam int idNew){
        try{
            Iterable<CommentaryDto> commentaryDtoArrayList = newService.getCommentaries(idNew);
            return new ResponseEntity<>(commentaryDtoArrayList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
