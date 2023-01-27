package com.mini.orm.miniorm.model;

import com.mini.orm.miniorm.util.MyCustomAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * lc = lower case
 * uc = upper case
 *
 * I'm naming it this way just for testing purposes
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@MyCustomAnnotation
public class Customer {

    private int lcInt;
    private String ucString;

    private double lcDouble;

    private float lcFloat;

    private char lcChar;

    private long lcLong;

    private Integer ucInteger;

    private Long ucLong;
}
