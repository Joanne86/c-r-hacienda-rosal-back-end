package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.MessageDto;
import cr.hacienda.rosal.entities.User;

import java.util.ArrayList;

public interface INotificationService {
    void save(Iterable<User> users);
    void sendMessageToAllResidents(String message);
    void sendMessageToAllDebtors(String message);
    void sendMessageToOne(MessageDto messageDto);
    void addAllNumbers(ArrayList<String> cellphones);
    void addDebtorsNumber(ArrayList<String> cellphones);
    void getAllNumber();
    void getAllDebtorsNumber();
    void deleteNumber();
}
