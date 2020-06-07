package com.mits4u.transactionsdemo.service.jpa;

import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity(name = "NAMES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Name {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "first_name")
    private String name;

}
