package org.example.SpringJPA;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
    -> JPA (java persistence API) ek java framework hai jo database ke sath kaam karna easy bana deta hai..
    -> no need to write plain Database queries and we write only java methods to get our work done.
    -> works only for relational databases
    -> Controller → Service → Repository → Database
    ->  save() : Insert and Update
    -> findById():
    -> findAll() : select * from student wali cheez.
    -> deleteById(): delete user where id = 1 wali command;
        Database	Java
        Table	Entity
        Column	Field
        Row	Object
    -> it must not include:
        - Business logic
        - Calculations
        - if-else based rules
        - Validation
    For joins in SQL we must use OneToMany(mappedBy= "student") and ManyToOne
    use @Query("sql query") to give sql queries directly this is JPQL(java persistence query language).


    @OneToMany(mappedBy = "user")
    List<Order> orders;
    this means that one user may multiple orders.

    ORM is object relational mapping.
    -> given table primary key is written inside of the object and ManyToOne join column where we have to define another
        entry from different table ki primary key then it will be foreign key.

*/
@Entity
@Table(name="student")
class student{
    @Id
    private Integer id;
    private String name;
    public void setId(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}

public class springJPADemo {

}
