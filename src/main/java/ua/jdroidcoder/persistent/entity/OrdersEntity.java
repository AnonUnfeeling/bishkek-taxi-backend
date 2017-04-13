package ua.jdroidcoder.persistent.entity;

import ua.jdroidcoder.persistent.dto.OrderDto;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jdroidcoder on 07.04.17.
 */
@Entity
@Table(name = "orders")
public class OrdersEntity extends AbstractEntity<Long> {
    private static long serialVersionUID = 4646764645484896L;
    @Column(name = "point_a")
    private String pointA;
    @Column(name = "point_b")
    private String pointB;
    @Column(name = "time")
    private Date time;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "status")
    private String status;
    @Column(name = "driver_phone")
    private String driverPhone;

    public OrdersEntity() {
    }

    public OrdersEntity(String pointA, String pointB, Date time, String userPhone, String status,
                        String driverPhone) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.time = time;
        this.userPhone = userPhone;
        this.status = status;
        this.driverPhone = driverPhone;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public OrdersEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public OrdersEntity setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
        return this;
    }

    @Override
    public String toString() {
        return "OrdersEntity{" +
                "pointA='" + pointA + '\'' +
                ", pointB='" + pointB + '\'' +
                ", time='" + time + '\'' +
                ", seat_count='" + status + '\'' +
                '}';
    }

    public OrderDto clone() {
        return new OrderDto(getPointA(), getPointB(), getTime().getTime(), getUserPhone(), getStatus(), getId(), getDriverPhone());
    }
}
