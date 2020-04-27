package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.CommentaryDto;
import cr.hacienda.rosal.dto.NewsDto;
import cr.hacienda.rosal.entities.Commentary;
import cr.hacienda.rosal.entities.News;
import cr.hacienda.rosal.repository.CommentaryRepository;
import cr.hacienda.rosal.repository.NewRepository;
import cr.hacienda.rosal.service.INewService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class NewServicesImpl implements INewService {

    private Logger logger = LoggerFactory.getLogger(NewServicesImpl.class);

    @Autowired
    NewRepository newRepository;

    @Autowired
    CommentaryRepository commentaryRepository;

    @Autowired
    MapperDtos mapperDtos;

    @Override
    public void publish(News news){
        logger.info("Guardando publicacion nueva");

        news.setPublish(mapperDtos.getDateString(new Date()));

        logger.info("Guardando publicacion: {}", news.toString());
        newRepository.save(news);
    }

    @Override
    public Iterable<CommentaryDto> getCommentaries(int idNew) {
        return mapperDtos.mapCommetaryToCommentaryDtoList(commentaryRepository.findAllByIdNews(idNew));
    }

    @Override
    public Iterable<NewsDto> getNews() {
        return mapNewsToNewsDto(newRepository.findAll());
    }

    private Iterable<NewsDto> mapNewsToNewsDto(Iterable<News> news){
        ArrayList<NewsDto> newsDtoArrayList = new ArrayList<>();
        for(News n: news){
            newsDtoArrayList.add(getNewsDto(n));
        }
        return newsDtoArrayList;
    }

    private NewsDto getNewsDto (News news){
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setPublish(news.getPublish());
        newsDto.setInformation(news.getInformation());
        ArrayList<Commentary> commentaries = (ArrayList<Commentary>) commentaryRepository.findAllByIdNews(news.getId());
        newsDto.setCommentaries(commentaries.size());
        return newsDto;
    }

    @Override
    public void saveCommentary(CommentaryDto commentaryDto) {
        commentaryRepository.save(mapperDtos.getCommentary(commentaryDto));
    }

    @Override
    public NewsDto updatePublish(NewsDto newsDto) {

        Optional<News> news = newRepository.findById(newsDto.getId());
        if(news.isPresent()){
            news.get().setInformation(newsDto.getInformation());
            news.get().setPublish(mapperDtos.getDateString(new Date()));
            newRepository.save(news.get());
            newsDto.setPublish(news.get().getPublish());
        }
        return newsDto;
    }
}
