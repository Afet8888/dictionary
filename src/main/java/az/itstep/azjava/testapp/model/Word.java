package az.itstep.azjava.testapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//Postgre: SERIAL, MySql INT AUTOINCREMENT
    private String en;
    private String az;
}
