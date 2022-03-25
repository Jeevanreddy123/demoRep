package com.microservices.demo.POJO;

import lombok.*;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String email;
    private String phoneNum;
    private String userName;
}
