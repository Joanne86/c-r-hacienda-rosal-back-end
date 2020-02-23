package cr.hacienda.rosal.utils;

import cr.hacienda.rosal.dto.UserDto;
import cr.hacienda.rosal.entities.Session;
import cr.hacienda.rosal.entities.User;
import cr.hacienda.rosal.entities.UserType;

import java.util.ArrayList;

public class Mapper {

    /**
     * Metodo para mapear los datos que se guardaran en base de datos USER
     * @param users dto a mapear
     * @return lista a guardar en base de datos User
     */
    public static ArrayList<User> mapUsers(ArrayList<UserDto> users){

        ArrayList<User> usersDB = new ArrayList<>();
        for (UserDto u: users) {
            User user = new User();
            Session session = new Session();
            UserType userType = new UserType();

            session.setPassword(u.getDocumentNumber());
            session.setUser(u.getDocumentNumber());

            userType.setRol(u.getUserType());

            user.setCellphone(u.getCellphone());
            user.setDocumentNumber(u.getDocumentNumber());
            user.setFirstName(u.getFirstName());
            user.setSecondName(u.getSecondName());
            user.setLastName(u.getLastName());

            user.setSession(session);
            user.setUserType(userType);
            usersDB.add(user);
        }
        return usersDB;
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
}
