package ua.jdroidcoder.persistent.entity;

import ua.jdroidcoder.persistent.dto.UserCoordinateDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jdroidcoder on 10.04.17.
 */
@Entity
@Table(name = "user_coordinate")
public class UserCoordinateEntity extends AbstractEntity<Long> {
    @Column(name = "user_phone", unique = true)
    private String userPhone;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lng")
    private Double lng;

    public UserCoordinateEntity() {
    }

    public UserCoordinateEntity(String userPhone, Double lat, Double lng) {
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

    public UserCoordinateDto clone() {
        return new UserCoordinateDto(getUserPhone(), getLat(), getLng());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCoordinateEntity)) return false;
        if (!super.equals(o)) return false;

        UserCoordinateEntity that = (UserCoordinateEntity) o;

        return userPhone != null ? userPhone.equals(that.userPhone) : that.userPhone == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        return result;
    }
}
