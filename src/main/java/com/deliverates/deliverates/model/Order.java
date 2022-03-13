package com.deliverates.deliverates.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = {"order_number"}, name = "orders_unique_order_number_idx")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Order extends AbstractBaseEntity{

    @Column(name = "order_number", nullable = false)
    @NotNull
    private Integer orderNumber;

    @Column(name = "time_waited", nullable = false)
    @NotNull
    private LocalTime timeWaited;

    @Column(name = "date_time_reported", nullable = false)
    @NotNull
    private LocalDateTime dateTimeReported;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    //@JsonBackReference
    private Restaurant restaurant;

    public Order(Integer id, Integer orderNumber, LocalTime timeWaited, LocalDateTime dateTimeReported) {
        super(id);
        this.orderNumber = orderNumber;
        this.timeWaited = timeWaited;
        this.dateTimeReported = dateTimeReported;
    }
}
