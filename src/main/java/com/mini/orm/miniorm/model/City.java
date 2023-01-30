package com.mini.orm.miniorm.model;

import com.mini.orm.miniorm.util.MyCustomAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MyCustomAnnotation
public class City {

    private Long id;
    private String name;
    private String continent;
}
