package ua.jdroidcoder.persistent.dto;

import ua.jdroidcoder.persistent.entity.UserCoordinateEntity;

import javax.persistence.Column;

/**
 * Created by jdroidcoder on 10.04.17.
 */
public class UserCoordinateDto {
    private String userPhone;
    private Double lat;
    private Double lng;

    public UserCoordinateDto() {
    }

    public UserCoordinateDto(String userPhone, Double lat, Double lng) {
        this.userPhone = userPhone;
        this.lat = lat;
        this.lng = lng;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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
        return new UserCoordinateEntity(getUserPhone()
                , getLat(), getLng());
    }
}
