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
    private double[] pointACoordinate;
    @NotEmpty(message = "The destination cannot be empty")
    private String pointB;
    private double[] pointBCoordinate;
    private Long time;
    @NotEmpty(message = "The user phone cannot be empty")
    private String userPhone;
    @NotEmpty(message = "The status cannot be empty")
    private String status;
    private String driverPhone;
    private Long acceptDate;

    public OrderDto() {
    }

    public OrderDto(String pointA, double[] pointACoordinate,
                    String pointB, double[] pointBCoordinate,
                    Long time, String userPhone, String status,
                    Long id, String driverPhone, Date acceptDate) {
        this.id = id;
        this.pointA = pointA;
        this.pointB = pointB;
        this.time = time;
        this.userPhone = userPhone;
        this.status = status;
        this.driverPhone = driverPhone;
        this.pointACoordinate = pointACoordinate;
        this.pointBCoordinate = pointBCoordinate;
        this.acceptDate = acceptDate.getTime();
    }
    public OrderDto(String pointA, double[] pointACoordinate,
                    String pointB, double[] pointBCoordinate,
                    Long time, String userPhone, String status,
                    Long id, String driverPhone) {
        this.id = id;
        this.pointA = pointA;
        this.pointB = pointB;
        this.time = time;
        this.userPhone = userPhone;
        this.status = status;
        this.driverPhone = driverPhone;
        this.pointACoordinate = pointACoordinate;
        this.pointBCoordinate = pointBCoordinate;
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

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public double[] getPointACoordinate() {
        return pointACoordinate;
    }

    public void setPointACoordinate(double[] pointACoordinate) {
        this.pointACoordinate = pointACoordinate;
    }

    public double[] getPointBCoordinate() {
        return pointBCoordinate;
    }

    public void setPointBCoordinate(double[] pointBCoordinate) {
        this.pointBCoordinate = pointBCoordinate;
    }

    public Long getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Long acceptDate) {
        this.acceptDate = acceptDate;
    }

    public OrdersEntity clone() {
        return new OrdersEntity(getPointA(), getPointB(), new Date(getTime()),
                getUserPhone(), getStatus(), getDriverPhone(),
                getPointACoordinate(), getPointBCoordinate(),new Date(getAcceptDate()));
    }
}
