package cr.hacienda.rosal.utils;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Debt;
import cr.hacienda.rosal.entities.Home;
import cr.hacienda.rosal.entities.User;
import cr.hacienda.rosal.entities.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class MapperDtos {

    private static Logger logger = LoggerFactory.getLogger(MapperDtos.class);

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

    public static User getUser(UserDto u){
        logger.info("Inicia mapeo de userDto a User");
        User user = new User();

        user.setDocumentNumber(u.getDocumentNumber());
        UserType userType = new UserType();
        userType.setId(1);
        userType.setRol(Mapps.getUserType().get(1));
        user.setCellphone(u.getCellphone());
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
            cellphones.add("+57".concat(u.getCellphone()));
        }
        return cellphones;
    }

    public static Iterable<Home> mapHomes(ArrayList<UserDto> users){
        ArrayList<Home> homes = new ArrayList<>();
        for (UserDto u: users){
            homes.add(getHome(u));
        }
        return homes;
    }

    public static Home getHome(UserDto userDto){
        Home home = new Home();
        home.setTowerNumberHome(userDto.getTowerNumberHome());
        home.setUser(getUser(userDto));
        home.setDebt(getDebt(userDto));
        return home;
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
        userDto.setMonths(home.getDebt().getMonths());
        userDto.setDebt(home.getDebt().getAmount());
        return userDto;
    }

    public static ArrayList<Debt> mapUserDtoToDebt(ArrayList<UserDto> users){
        ArrayList<Debt> debts = new ArrayList<>();

        for (UserDto u: users){
            debts.add(getDebt(u));
        }
        return debts;
    }
    public static Debt getDebt(UserDto userDto){
        Debt debt = new Debt();
        debt.setTowerNumberHome(userDto.getTowerNumberHome());
        debt.setAmount(userDto.getDebt());
        debt.setMonths(userDto.getMonths());
        return debt;
    }
}
