package com.improvingskills.dao;

import com.improvingskills.entities.Direction;
import com.improvingskills.entities.Employee;

import java.util.List;

public interface DirectionDAO {
    List<Direction> findAll();
    Direction findById(Long Id);

    Direction create(Direction direction);

    Direction update(Direction direction);
    boolean deleteById(Long id);

}
