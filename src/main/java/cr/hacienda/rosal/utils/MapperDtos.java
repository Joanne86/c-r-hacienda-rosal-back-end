package cr.hacienda.rosal.utils;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Home;
import cr.hacienda.rosal.entities.Session;
import cr.hacienda.rosal.entities.User;
import cr.hacienda.rosal.entities.UserType;

import java.util.ArrayList;

public class MapperDtos {

    /**
     * Metodo para mapear los datos que se guardaran en base de datos USER
     * @param users dto a mapear
     * @return lista a guardar en base de datos User
     */
    public static Iterable<User> mapUserDtoToUser(ArrayList<UserDto> users){

        ArrayList<User> usersDB = new ArrayList<>();
        for (UserDto u: users) {
            usersDB.add(getUser(u));
        }
        return usersDB;
    }

    private static User getUser(UserDto u){
        User user = new User();
        Session session = new Session();
        UserType userType = new UserType();

        session.setPassword(u.getDocumentNumber());
        session.setUsername(u.getDocumentNumber());

        userType.setId(u.getUserType());
        userType.setRol(Mapps.getUserType().get(u.getUserType()));

        user.setCellphone(u.getCellphone());
        user.setDocumentNumber(u.getDocumentNumber());
        user.setFirstName(u.getFirstName());
        user.setSecondName(u.getSecondName());
        user.setLastName(u.getLastName());

        user.setSession(session);
        user.setUserType(userType);
        return user;
    }

    /**
     * Metodo para mapear los numeros de celular a los que se va enviar mensajes de texto
     * @param users dto a meapear
     * @return lista de celulares
     */
    public static ArrayList<String> mapCellphones(ArrayList<UserDto> users){
        ArrayList<String> cellphones = new ArrayList<>();

        for (UserDto u: users){
            cellphones.add(u.getCellphone());
        }
        return cellphones;
    }

    public static Iterable<Home> mapHomes(ArrayList<UserDto> users){

        ArrayList<Home> homes = new ArrayList<>();

        for (UserDto u: users){
            Home home = new Home();
            home.setTowerNumberHome(u.getTowerNumberHome());
            home.setUser(getUser(u));
            homes.add(home);
        }
        return homes;
    }

    public static Iterable<UserDto> mapUserToUserDto(Iterable<Home> homes){
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for(Home h: homes){
            userDtos.add(getUserDto(h));
        }
        return userDtos;
    }

    private static UserDto getUserDto(Home home){
        UserDto userDto = new UserDto();

        userDto.setTowerNumberHome(home.getTowerNumberHome());
        userDto.setDocumentNumber(home.getUser().getDocumentNumber());
        userDto.setCellphone(home.getUser().getCellphone());
        userDto.setFirstName(home.getUser().getFirstName());
        userDto.setSecondName(home.getUser().getSecondName());
        userDto.setLastName(home.getUser().getLastName());
        userDto.setUserType(home.getUser().getUserType().getId());

        return userDto;
    }
}
