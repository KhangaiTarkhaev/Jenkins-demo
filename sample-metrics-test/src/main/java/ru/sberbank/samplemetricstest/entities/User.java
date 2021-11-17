package ru.sberbank.samplemetricstest.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class User {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

}
