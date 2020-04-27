package cr.hacienda.rosal.utils;

import cr.hacienda.rosal.dto.CommentaryDto;
import cr.hacienda.rosal.dto.DebtDto;
import cr.hacienda.rosal.dto.RequestDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.*;
import cr.hacienda.rosal.repository.DebtRepository;
import cr.hacienda.rosal.repository.HomeRepository;
import cr.hacienda.rosal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class MapperDtos {

    private static Logger logger = LoggerFactory.getLogger(MapperDtos.class);

    @Autowired
    DebtRepository debtRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HomeRepository homeRepository;

    /**
     * Metodo para mapear los datos que se guardaran en base de datos USER
     * @param users dto a mapear
     * @return lista a guardar en base de datos User
     */
    public Iterable<User> mapUserDtoToUser(ArrayList<UserDto> users){

        ArrayList<User> usersDB = new ArrayList<>();
        for (UserDto u: users) {
            usersDB.add(getUser(u));
        }
        return usersDB;
    }

    public ArrayList<Credential> mapUserDtosToCredentals(ArrayList<UserDto> userDtos){
        logger.info("Mapeo de las credenciales");
        ArrayList<Credential> credentials = new ArrayList<>();

        for(UserDto u: userDtos){
            credentials.add(getCredentialOfUserDto(u));
        }
        return credentials;
    }

    public  UserDto getUserDtoOfCredential(Credential credential){
        UserDto userDto = getUserDto(credential.getHome());
        userDto.setIdSession(credential.getId());
        userDto.setUser(credential.getUser());
        userDto.setPassword(credential.getPassword());
        return userDto;
    }

    public  Credential getCredentialOfUserDto(UserDto userDto){
        Credential credential = new Credential();
        credential.setUser(userDto.getUser());
        credential.setPassword(userDto.getPassword());
        credential.setHome(getHomeToSave(userDto));
        return credential;
    }

    public  User getUser(UserDto u){
        logger.info("Inicia mapeo de userDto a User");

        User user = new User();
        UserType userType = new UserType();
        userType.setId(1);
        userType.setRol(Mapps.getUserType().get(1));

        user.setDocumentNumber(u.getDocumentNumber());
        user.setCellphone(u.getCellphone());
        user.setName(u.getName());
        user.setUserType(userType);

        logger.info("termina mapeo de user id: {}", user.getId());
        return user;
    }

    public  User getUserToSave(UserDto u){
        logger.info("Inicia mapeo de userDto a User");
        User user = getUser(u);
        user.setId(userRepository.getIdByDocumentNumber(u.getDocumentNumber()));
        return user;
    }

    /**
     * Metodo para mapear los numeros de celular a los que se va enviar mensajes de texto
     * @param users dto a meapear
     * @return lista de celulares
     */
    public  ArrayList<String> mapCellphones(ArrayList<UserDto> users){
        ArrayList<String> cellphones = new ArrayList<>();

        for (UserDto u: users){
            cellphones.add("+57".concat(u.getCellphone()));
        }
        return cellphones;
    }

    public  Iterable<Home> mapHomes(ArrayList<UserDto> users){
        ArrayList<Home> homes = new ArrayList<>();
        for (UserDto u: users){
            homes.add(getHomeToFirstSave(u));
        }
        return homes;
    }

    public  Iterable<Home> mapHomesToSave(ArrayList<UserDto> users){
        ArrayList<Home> homes = new ArrayList<>();
        for (UserDto u: users){
            homes.add(getHomeToSave(u));
        }
        return homes;
    }

    public Home getHomeToSave(UserDto userDto){
        Home home = new Home();
        home.setId(homeRepository.getIdByTowerNumberHome(userDto.getTowerNumberHome()));
        home.setTowerNumberHome(userDto.getTowerNumberHome());
        home.setUser(getUserToSave(userDto));
        home.setDebt(getDebtToSave(userDto));
        return home;
    }

    public  Home getHome(UserDto userDto){
        Home home = new Home();
        home.setTowerNumberHome(userDto.getTowerNumberHome());
        home.setUser(getUser(userDto));
        home.setDebt(getDebt(userDto));
        return home;
    }

    public  Home getHomeToFirstSave(UserDto userDto){
        Home home = new Home();
        home.setTowerNumberHome(userDto.getTowerNumberHome());
        home.setUser(getUserToSave(userDto));
        home.setDebt(getDebtToSave(userDto));
        return home;
    }

    public  Iterable<UserDto> mapUserToUserDto(Iterable<Home> homes){
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for(Home h: homes){
            userDtos.add(getUserDto(h));
        }
        return userDtos;
    }

    public  UserDto getUserDto(Home home){
        UserDto userDto = new UserDto();

        userDto.setIdUser(home.getUser().getId());
        userDto.setTowerNumberHome(home.getTowerNumberHome());
        userDto.setDocumentNumber(home.getUser().getDocumentNumber());
        userDto.setCellphone(home.getUser().getCellphone());
        userDto.setName(home.getUser().getName());
        userDto.setUserType(home.getUser().getUserType().getId());
        userDto.setMonths(home.getDebt().getMonths());
        userDto.setDebt(home.getDebt().getAmount());
        return userDto;
    }

    public  ArrayList<Debt> mapUserDtoToDebt(ArrayList<UserDto> users){
        ArrayList<Debt> debts = new ArrayList<>();

        for (UserDto u: users){
            debts.add(getDebt(u));
        }
        return debts;
    }
    public  Debt getDebtToSave(UserDto userDto){
        Debt debt = getDebt(userDto);
        debt.setId(debtRepository.getIdByTowerNumberHome(userDto.getTowerNumberHome()));
        return debt;
    }

    public  Debt getDebt(UserDto userDto){
        Debt debt = new Debt();
        debt.setTowerNumberHome(userDto.getTowerNumberHome());
        debt.setAmount(userDto.getDebt());
        debt.setMonths(userDto.getMonths());
        return debt;
    }

    public  DebtDto getDebt(Optional<Debt> debt){
        DebtDto debtDto = new DebtDto();
        if(debt.isPresent()){
            debtDto.setAmount(debt.get().getAmount());
            debtDto.setMonths(debt.get().getMonths());
            debtDto.setTowerNumberHome(debt.get().getTowerNumberHome());
        }
        return debtDto;
    }

    public  Iterable<CommentaryDto> mapCommetaryToCommentaryDtoList(Iterable<Commentary> commentaryList){
        ArrayList<CommentaryDto> commentaryDtoList = new ArrayList<>();

        for (Commentary c: commentaryList){
            commentaryDtoList.add(getCommentaryDto(c));
        }
        return commentaryDtoList;
    }

    private  CommentaryDto getCommentaryDto(Commentary commentary){
        CommentaryDto commentaryDto = new CommentaryDto();

        commentaryDto.setId(commentary.getId());
        commentaryDto.setMessage(commentary.getMessage());
        commentaryDto.setDocument(commentary.getUser().getDocumentNumber());
        commentaryDto.setName(commentary.getUser().getName());
        commentaryDto.setPublishDate(commentary.getPublishDate());
        commentaryDto.setIdNews(commentary.getNews().getId());
        return commentaryDto;
    }

    public  Commentary getCommentary(CommentaryDto commentaryDto){

        Commentary commentary = new Commentary();
        commentary.setMessage(commentaryDto.getMessage());
        commentary.setPublishDate(getDateString(new Date()));

        User user = new User();
        user.setId(userRepository.getIdByDocumentNumber(commentaryDto.getDocument()));
        user.setDocumentNumber(commentaryDto.getDocument());
        user.setCellphone(commentaryDto.getCellphone());
        user.setName(commentaryDto.getName());

        UserType userType = new UserType();
        userType.setId(1);
        userType.setRol(Mapps.getUserType().get(1));

        user.setUserType(userType);
        commentary.setUser(user);

        News news = new News();
        news.setId(commentaryDto.getIdNews());
        news.setInformation(commentaryDto.getInformation());
        news.setPublish(commentaryDto.getPublishDate());

        commentary.setNews(news);

        return commentary;
    }

    public  String getDateString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public  Iterable<RequestDto> mapRequestDto(Iterable<Request> requestList) {
        ArrayList<RequestDto> requestDtoList = new ArrayList<>();
        for(Request r: requestList){
            requestDtoList.add(getRequestDto(r));
        }
        return requestDtoList;
    }

    public  RequestDto getRequestDto(Request request){
        RequestDto requestDto = new RequestDto();
        requestDto.setId(request.getId());
        requestDto.setMessage(request.getMessage());
        requestDto.setPublishDate(request.getPublishDate());
        requestDto.setResponse(request.getResponse());
        requestDto.setType(request.getTypeRequest().getId());
        requestDto.setUserDto(getUserDto(request));
        requestDto.setState(request.getStateRequest().getState());
        return requestDto;
    }

    public  UserDto getUserDto(Request request){
        return getUserDto(request.getHome());
    }

    public  Request getRequest(RequestDto requestDto) {
        logger.info("Comienza mapeo del request");
        Request request = new Request();
        request.setMessage(requestDto.getMessage());
        request.setPublishDate(getDateString(new Date()));

        StateRequest stateRequest = new StateRequest();
        stateRequest.setId(2);

        stateRequest.setState(Mapps.getSateRequest().get(2));
        request.setStateRequest(stateRequest);

        request.setHome(getHomeToSave(requestDto.getUserDto()));

        TypeRequest typeRequest = new TypeRequest();
        typeRequest.setId(requestDto.getType());
        typeRequest.setAffair(Mapps.getTypeRequest().get(requestDto.getType()));

        request.setTypeRequest(typeRequest);
        logger.info("Termina mapeo del request");
        return request;
    }
}
