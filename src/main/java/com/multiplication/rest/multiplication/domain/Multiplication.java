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
 * This class represents a Multiplication (a * b).
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class Multiplication {

    // Both factors
    private final int factorA;
    private final int factorB;
    @Id
    @GeneratedValue
    @Column(name="MULTIPLICATION_ID")
    private final Long id;

    // Empty constructor for JSON (de)serialization
    Multiplication() {
        this(0, 0, 0L);
    }
}
