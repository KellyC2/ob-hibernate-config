package com.improvingskills.dao;

import com.improvingskills.entities.Car;
import com.improvingskills.entities.Employee;

public interface CarDAO {

    Car findById(Long id);
    Car create(Car car);
    Boolean deleteById(Long car);
}
