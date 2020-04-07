package cr.hacienda.rosal.service;

import cr.hacienda.rosal.dto.RequestDto;

public interface IRequestService {
    void sendRequest(RequestDto requestDto);
}
