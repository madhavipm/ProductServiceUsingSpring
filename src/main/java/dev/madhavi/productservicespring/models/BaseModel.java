package dev.madhavi.productservicespring.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass//no obj is created for base model
public class BaseModel implements Serializable {
    //I'm using redis :serializable is an interface when u send data over the network
    //my java objs get converted to json--when i get response from json it will convert
    //back to java -Conversion to java to json-SERIALIZABLE
    //JSON - JAVA - DESERIALIZABLE..Interface is just a signal dont have any implementation
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Auto increment
    private Long id;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
