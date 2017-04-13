package ua.jdroidcoder.persistent.entity;

import ua.jdroidcoder.persistent.dto.UserDto;

import javax.persistence.*;

/**
 * Created by jdroidcoder on 07.04.17.
 */
@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity<Long> {
    private static long serialVersionUID = 4646464645484896L;

    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    UserProfileEntity userProfileEntity;

    public UserEntity() {
    }

    public UserProfileEntity getUserProfileEntity() {
        return userProfileEntity;
    }

    public UserEntity setUserProfileEntity(UserProfileEntity userProfileEntity) {
        this.userProfileEntity = userProfileEntity;
        return this;
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

    public UserDto clone() {
        UserDto userDto = new UserDto();
        userDto.setEmail(getEmail());
        userDto.setPassword(getPassword());
        userDto.setId(getId());
        return userDto;
    }
}
