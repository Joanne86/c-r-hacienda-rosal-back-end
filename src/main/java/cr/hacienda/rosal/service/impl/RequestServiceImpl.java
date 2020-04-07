package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.RequestDto;
import cr.hacienda.rosal.repository.RequestRepository;
import cr.hacienda.rosal.service.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestServiceImpl implements IRequestService {

    @Autowired
    RequestRepository requestRepository;

    @Override
    public void sendRequest(RequestDto requestDto) {
//maper
    }
}
