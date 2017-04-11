package ua.jdroidcoder.persistent.dto;

import org.hibernate.validator.constraints.NotEmpty;
import ua.jdroidcoder.persistent.entity.UserEntity;
import ua.jdroidcoder.persistent.entity.UserProfileEntity;

import javax.persistence.Column;
import javax.validation.constraints.Size;

/**
 * Created by jdroidcoder on 07.04.17.
 */
public class UserDto {
    @NotEmpty(message = "Email must be not empty")
    private String email;
    @Size(min = 6, message = "Password is small")
    private String password;
    private Long id;
    private UserProfileEntity profileEntity;

    public UserDto() {
    }

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserProfileEntity getProfileEntity() {
        return profileEntity;
    }

    public void setProfileEntity(UserProfileEntity profileEntity) {
        this.profileEntity = profileEntity;
    }

    public UserEntity clone() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(getEmail());
        userEntity.setPassword(getPassword());
//        userEntity.setUserProfileEntity(new UserProfileEntity("firstName","lastname","123"));
        return userEntity;
    }
}
