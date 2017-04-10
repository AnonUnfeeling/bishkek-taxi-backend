package ua.jdroidcoder.service;

import ua.jdroidcoder.persistent.dto.UserCoordinateDto;

/**
 * Created by jdroidcoder on 10.04.17.
 */
public interface UserCoordinateService {
    UserCoordinateDto setCoordinate(UserCoordinateDto coordinateDto);

    UserCoordinateDto getCoordinate(String userEmail);
}
