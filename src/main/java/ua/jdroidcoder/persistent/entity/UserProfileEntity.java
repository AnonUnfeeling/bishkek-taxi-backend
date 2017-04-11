package ua.jdroidcoder.persistent.entity;

import ua.jdroidcoder.persistent.dto.UserProfileDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by jdroidcoder on 07.04.17.
 */
@Entity
@Table(name = "user_profile")
public class UserProfileEntity extends AbstractEntity<Long> {
    private static long serialVersionUID = 4646455645484896L;

    @Column(name = "first_name")
    private String firtName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone", unique = true)
    private String phone;
    @Column(name = "email", unique = true)
    private String email;

    public UserProfileEntity() {
    }

    public UserProfileEntity(String firtName, String lastName, String phone) {
        this.firtName = firtName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfileDto clone() {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setPhone(getPhone());
        userProfileDto.setLastName(getLastName());
        userProfileDto.setFirstName(getFirtName());
        userProfileDto.setEmail(getEmail());
        return userProfileDto;
    }
}
