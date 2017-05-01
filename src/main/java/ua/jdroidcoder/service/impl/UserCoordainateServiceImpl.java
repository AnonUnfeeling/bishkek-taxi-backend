package ua.jdroidcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.jdroidcoder.persistent.dto.UserCoordinateDto;
import ua.jdroidcoder.persistent.entity.UserCoordinateEntity;
import ua.jdroidcoder.persistent.repository.UserCoordinateRepository;
import ua.jdroidcoder.service.UserCoordinateService;

/**
 * Created by jdroidcoder on 10.04.17.
 */
@Service
public class UserCoordainateServiceImpl implements UserCoordinateService {
    private UserCoordinateRepository coordinateRepository;

    @Autowired
    public UserCoordainateServiceImpl(UserCoordinateRepository coordinateRepository) {
        this.coordinateRepository = coordinateRepository;
    }

    @Override
    public UserCoordinateDto setCoordinate(UserCoordinateDto coordinateDto) {
        try {
            UserCoordinateEntity coordinateEntity = coordinateRepository.findUserCoordinateByUserPhone(coordinateDto.getUserPhone());
            if (coordinateEntity == null) {
                return coordinateRepository.save(coordinateDto.clone()).clone();
            } else {
                coordinateEntity.setLat(coordinateDto.getLat());
                coordinateEntity.setLng(coordinateDto.getLng());
                return coordinateRepository.save(coordinateEntity).clone();
            }
        } catch (Exception e) {
            return coordinateDto;
        }
    }

    @Override
    public UserCoordinateDto getCoordinate(String userPhone) {
        try {
            return coordinateRepository.findUserCoordinateByUserPhone(userPhone).clone();
        } catch (Exception e) {
            return null;
        }
    }
}
