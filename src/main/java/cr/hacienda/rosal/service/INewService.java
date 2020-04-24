package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.CommentaryDto;
import cr.hacienda.rosal.dto.NewsDto;
import cr.hacienda.rosal.entities.News;

import java.text.ParseException;

public interface INewService {
    void publish(News news) throws ParseException;
    Iterable<CommentaryDto> getCommentaries(int idNew);
    Iterable<NewsDto> getNews();
    void saveCommentary(CommentaryDto commentaryDto);
    NewsDto updatePublish(NewsDto newsDto);
}
