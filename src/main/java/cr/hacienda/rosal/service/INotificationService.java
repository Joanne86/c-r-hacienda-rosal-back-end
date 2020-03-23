package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.MessageDto;

import java.util.ArrayList;

public interface INotificationService {
    void sendMessageToAllResidents(String message);
    void sendMessageToAllDebtors(String message);
    void sendMessageToOne(MessageDto messageDto);
    void addAllNumbers(ArrayList<String> cellphones);
    void addDebtorsNumber(ArrayList<String> cellphones);
    void addNumber(String cellphone);
    void addDebtorNumber(String cellphone);
    void getAllNumber();
    void getAllDebtorsNumber();
    void deleteNumber(String cellphone);
}
