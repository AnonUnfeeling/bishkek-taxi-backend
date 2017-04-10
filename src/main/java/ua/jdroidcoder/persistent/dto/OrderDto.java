package ua.jdroidcoder.persistent.dto;

import org.hibernate.validator.constraints.NotEmpty;
import ua.jdroidcoder.persistent.entity.OrdersEntity;

import java.util.Date;

/**
 * Created by jdroidcoder on 07.04.17.
 */
public class OrderDto {
    private Long id;

    @NotEmpty(message = "The address cannot be empty")
    private String pointA;

    @NotEmpty(message = "The destination cannot be empty")
    private String pointB;

    @NotEmpty(message = "The time cannot be empty")
    private Long time;

    @NotEmpty(message = "The user phone cannot be empty")
    private String userPhone;

    @NotEmpty(message = "The status cannot be empty")
    private String status;

    public OrderDto() {
    }

    public OrderDto(String pointA, String pointB, Long time, String userPhone, String status, Long id) {
        this.id = id;
        this.pointA = pointA;
        this.pointB = pointB;
        this.time = time;
        this.userPhone = userPhone;
        this.status = status;
    }

    public String getPointA() {
        return pointA;
    }

    public void setPointA(String pointA) {
        this.pointA = pointA;
    }

    public String getPointB() {
        return pointB;
    }

    public void setPointB(String pointB) {
        this.pointB = pointB;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdersEntity clone() {
        return new OrdersEntity(getPointA(), getPointB(), new Date(getTime()), getUserPhone(), getStatus());
    }
}
