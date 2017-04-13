package ua.jdroidcoder.persistent.dto;

import org.hibernate.validator.constraints.NotEmpty;
import ua.jdroidcoder.persistent.entity.UserProfileEntity;

import javax.validation.constraints.Size;

/**
 * Created by jdroidcoder on 07.04.17.
 */
public class UserProfileDto {
    @NotEmpty(message = "First name must be not empty")
    private String firstName;
    @NotEmpty(message = "Last name must be not empty")
    private String lastName;
    @NotEmpty(message = "Please, write your number ")
    @Size(max = 6)
    private String phone;
    private String email;

    public UserProfileDto() {
    }

    public UserProfileDto(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public UserProfileEntity clone(){
        UserProfileEntity userProfileEntity = new UserProfileEntity();
        userProfileEntity.setPhone(getPhone());
        userProfileEntity.setFirtName(getFirstName());
        userProfileEntity.setLastName(getLastName());
        userProfileEntity.setEmail(getEmail());
        return userProfileEntity;
    }
}
