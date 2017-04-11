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
    @Column(name = "user_email", unique = true)
    private String userEmail;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lng")
    private Double lng;

    public UserCoordinateEntity() {
    }

    public UserCoordinateEntity(String userEmail, Double lat, Double lng) {
        this.userEmail = userEmail;
        this.lat = lat;
        this.lng = lng;
    }

    public String getUserId() {
        return userEmail;
    }

    public void setUserId(String userEmail) {
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

    public UserCoordinateDto clone() {
        return new UserCoordinateDto(getUserId(), getLat(), getLng());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCoordinateEntity)) return false;
        if (!super.equals(o)) return false;

        UserCoordinateEntity that = (UserCoordinateEntity) o;

        return userEmail != null ? userEmail.equals(that.userEmail) : that.userEmail == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        return result;
    }
}
