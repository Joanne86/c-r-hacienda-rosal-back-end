package cr.hacienda.rosal.utils;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Home;
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
        UserType userType = new UserType();

        userType.setId(1);
        userType.setRol(Mapps.getUserType().get(1));

        user.setCellphone(u.getCellphone());
        user.setDocumentNumber(u.getDocumentNumber());
        user.setName(u.getName());

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

    public static UserDto getUserDto(Home home){
        UserDto userDto = new UserDto();

        userDto.setTowerNumberHome(home.getTowerNumberHome());
        userDto.setDocumentNumber(home.getUser().getDocumentNumber());
        userDto.setCellphone(home.getUser().getCellphone());
        userDto.setName(home.getUser().getName());
        userDto.setUserType(home.getUser().getUserType().getId());

        return userDto;
    }
}
