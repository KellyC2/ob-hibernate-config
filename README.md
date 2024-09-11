## Persistence:
1. Java Database Connectivity(JDBC).
2. JPA(Jakarta Persistence API), especification.
    Implementations:
    - Hibernate(ORM- Object Relational Mapping)
    - EclipseLink
3. Spring Data JPA

## Configuration
1. Add dependencies in the pom.xml file.
    - Hibernate-core dependency
    - Database connector or driver dependency.
2. Create SessionFactory object from Java, HibernateUtil class.
3. Create the "hibernate.cfg.xml" file i the src/main/resources  folder and configure it.
4. Create model and perform CRUD operations


##  CRUD Operations
Create Retrieve Update Delete

### Read Operations(Retrieve)
`SELECT * FROM Ttable ...`

### Write Operations(Create, Update, Delete)

Create:`INSERT INTO table () value ();`

Update: `UPDATE table SET column1=value1...`

Delete: `DELETE FROM table ...`

## Where to program the code thata interacts with the database:
In the Repository or DAO class.

General schema of web application with Hibernate:
Browser-->Controller-->Service-->DAO or Repository -->Database(MySQL, PostgreSQL, etc.)

## Populating the Database
Hibernate by default o automatically executes the "import.sql" file if it exists.

With the  "hibernate.hbm2ddl.import_files" property, you can instruct Hibernate to execute more than one SQL file.

You can insert new data from Java using the methods of the Session interface.

## Entity Associations
*One To One
    *@OneToOne
    *@JoinColumn
    *@JoinTable
    *@PrimaryKeyJoinColumn
    *@MapsId
*One To Many
*Many To One
*Many To Many

## Queries
*   Queries:
    *HQL
    *Native SQL
    *NamedQueries
    *Criteria API
    *Hibernate/JPA methods: find, persist,merge,remove, etc.

## Events Management
*Lifecycle callback Events: prePersist, preUpdate, preRemove, postPersist, etc.
*@Audited: At class/attribute level.
*Interceptors

## Database schema change migration
* https://wwww.liquibase.org/

* https://flywaydb.org/