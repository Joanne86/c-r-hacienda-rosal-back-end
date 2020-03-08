package cr.hacienda.rosal.utils;

import cr.hacienda.rosal.dto.DebtDto;
import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Debt;
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
            homes.add(getHome(u));
        }
        return homes;
    }

    public static Home getHome(UserDto userDto){
        Home home = new Home();
        home.setTowerNumberHome(userDto.getTowerNumberHome());
        home.setUser(getUser(userDto));
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

        return userDto;
    }

    public static Iterable<Debt> mapUserDtoToDebt(ArrayList<UserDto> users){
        ArrayList<Debt> debts = new ArrayList<>();

        for (UserDto u: users){
            Debt debt = new Debt();
            debt.setAmount(u.getDebt());
            debt.setMonths(u.getMonths());
            debt.setHome(getHome(u));
            debts.add(debt);
        }
        return debts;
    }

    public static ArrayList<UserDto> mapDebtToUserDto(Iterable<Debt> debtors) {
        ArrayList<UserDto> debtorList = new ArrayList<>();

        for (Debt d : debtors) {
            UserDto user = new UserDto();
            user.setUserType(d.getHome().getUser().getUserType().getId());
            user.setName(d.getHome().getUser().getName());
            user.setTowerNumberHome(d.getHome().getTowerNumberHome());
            user.setCellphone(d.getHome().getUser().getCellphone());
            user.setDebt(d.getAmount());
            user.setMonths(d.getMonths());
            user.setDocumentNumber(d.getHome().getUser().getDocumentNumber());
            debtorList.add(user);
        }
        return debtorList;
    }
    /*public static DebtDto getDebt(){

    }*/
}
