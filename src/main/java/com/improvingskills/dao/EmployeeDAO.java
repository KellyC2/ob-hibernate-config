package com.improvingskills.dao;


import com.improvingskills.dto.EmployeeDTO;
import com.improvingskills.entities.Employee;
import com.improvingskills.entities.EmployeeCategory;

import java.util.List;

/*Data Acces Object
Mètodos CRUD - Create Retrieve Update Dleete
 */
public interface EmployeeDAO {

    /**
     *Recuperar todos los empleados de la base de datos de la tabla employees
     * Utiliza HQL
     * @return Lista de empleados
     */
    List<Employee> findAll();

    /**
     *Recuperar todos los empleados de la base de datos de la tabla employees
     * Utiliza criteria
     * @return Lista de empleados
     */
    List<Employee> findAllCriteria();

    List<Employee> findAllNative();

    /**
     * Recupera empleados como objetos EmployeeDTO que son proyecciones,es decir, solo contienen ciertos campos, no todos.
     * @return
     */
    List<EmployeeDTO> findAllProjection();

    List<Employee> findMostPaid();


    /**
     * Devuelve el número de empleados en base de datos
     * @return
     */
    Long count();

    /**
     * Busca un empleado por su id
     * Utiliza métodos de Hibernate
     *@return empleado
     */
    Employee findById(Long Id);


    /**
     * Busca un empleado por su id cargando las relaciones many
     * Utiliza métodos de Hibernate
     * @param id
     * @return
     */
    Employee findByIdEager(Long id);

    /**
     * Busca un empleado por su id cargando las relacione many
     * Utiliza Criteria
     *@return empleado
     */
    Employee findByIdCriteria(Long Id);

    /**
     * Busca empleado por id
     * @param id
     * @return empleado
     */
    Employee findByIdNative(Long id);

    /**
     * Busca todos los emplados por la edad
     * @return lista de empleados de esa edad
     */
    List<Employee>findByAge(Integer age);

    Integer finAvgAgeCriteria();


    /**
     * Filtrar por apellido
     * Criteria utilizando like
     * @param lastName
     * @return
     */
    List<Employee> findByLastNameLikeCriteria(String lastName);


    /**
     * Filtrar por edad. Mayor de determinado número
     * Criteria
     * @param age
     * @return
     */
    List<Employee> findByAgeGreaterCriteria(Integer age);


    /**
     * Filtrar por rango de edad entre dos números
     * Criteria
     * @param min
     * @param max
     * @return
     */
    List<Employee> findByAgeBetweenCriteria(Integer min, Integer max);



    /**
     * Inserta un nuevo registro en la tabla employee
     * @param employee
     * @return
     * */

    /**
     * Buscar empleaados en un rango de edad y de determinado categoría.
     * @param ageMin
     * @param ageMax
     * @param category
     * @return
     */
    List<Employee>findByAgeBetweenCriteriaAndCategory (Integer ageMin, Integer ageMax, EmployeeCategory category);


    Employee create(Employee employee);

    /**
     * Actualiza un registro existente en la tabla employees
     * @param employee
     * @return
     */
    Employee update(Employee employee);

    /**
     *
     * @param id
     * @return
     */
    boolean deleteById(Long id);

}
