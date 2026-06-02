package com.tastee.tastee_backend.beans;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ingredient {
    
    @Id
    private Long id;

    @NonNull
    String name;
    

    int calories;

    
}
