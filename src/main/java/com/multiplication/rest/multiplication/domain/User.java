package com.multiplication.rest.multiplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Stores information to identify the user.
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class User {

    private final String alias;
    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private final Long id;

    // Empty constructor for JSON (de)serialization
    protected User() {
        id = null;
    	alias = null;

    }
}
