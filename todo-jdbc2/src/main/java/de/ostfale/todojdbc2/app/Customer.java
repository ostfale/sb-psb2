package de.ostfale.todojdbc2.app;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * Simple entity. Note that you don’t need getters or setters. It is perfectly okay to use them if you prefer to do so.
 * Really, the only requirement is that the entity has a property annotated with Id
 * (that is, @org.springframework.data.annotation.Id, not the javax.persistence one).
 *
 * Created :  25.09.2019
 *
 * @author : Uwe Sauerbrei
 */
public class Customer {

    @Id
    Long id;
    String firstName;
    LocalDate dob;
}
