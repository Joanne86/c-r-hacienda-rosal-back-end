package cr.hacienda.rosal.service.impl;

import cr.hacienda.rosal.dto.RequestDto;
import cr.hacienda.rosal.entities.Request;
import cr.hacienda.rosal.entities.StateRequest;
import cr.hacienda.rosal.repository.RequestRepository;
import cr.hacienda.rosal.service.IRequestService;
import cr.hacienda.rosal.utils.MapperDtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestServiceImpl implements IRequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    MapperDtos mapperDtos;

    @Override
        public void sendRequest(RequestDto requestDto) {
        requestRepository.save(mapperDtos.getRequest(requestDto));
    }

    @Override
    public Iterable<RequestDto> getAllRequest() {
        return mapperDtos.mapRequestDto(requestRepository.findAll());
    }

    @Override
    public void updateResponse(RequestDto requestDto) {
        Optional<Request> request = requestRepository.findAllById(requestDto.getId());
        if(request.isPresent()){
            StateRequest stateRequest = new StateRequest();
            stateRequest.setId(1);
            request.get().setStateRequest(stateRequest);
            request.get().setResponse(requestDto.getResponse());
            requestRepository.save(request.get());
        }
    }

    @Override
    public Iterable<RequestDto> findAllByTowerNumberHome(String towerNumberHome){
        return mapperDtos.mapRequestDto(requestRepository.findAllByTowerNumberHome(towerNumberHome));
    }
}
