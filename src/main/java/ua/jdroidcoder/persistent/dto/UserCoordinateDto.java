package ua.jdroidcoder.persistent.dto;

import ua.jdroidcoder.persistent.entity.UserCoordinateEntity;

import javax.persistence.Column;

/**
 * Created by jdroidcoder on 10.04.17.
 */
public class UserCoordinateDto {
    private String userEmail;
    private Double lat;
    private Double lng;

    public UserCoordinateDto() {
    }

    public UserCoordinateDto(String userEmail, Double lat, Double lng) {
        this.userEmail = userEmail;
        this.lat = lat;
        this.lng = lng;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public UserCoordinateEntity clone() {
        return new UserCoordinateEntity(getUserEmail()
                , getLat(), getLng());
    }
}
