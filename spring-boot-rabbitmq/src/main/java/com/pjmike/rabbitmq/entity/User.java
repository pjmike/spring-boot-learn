package com.pjmike.rabbitmq.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pjmike
 * @create 2018-10-09 15:00
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -4897260789140000100L;
    private Integer id;
    private String name;
}
