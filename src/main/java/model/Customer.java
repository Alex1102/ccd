package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import io.swagger.annotations.*;

@ApiModel
@SuppressWarnings("serial")
@XmlRootElement

// @Entity : deactivated, because follow exception
// Hibernate Caused by: org.hibernate.MappingException: Could not determine type for: java.util.List
// Fix my befor activating again
public class Customer implements Serializable {


    @Id
    @GeneratedValue
    private String id;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String name;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    // Can be Null or Empty at customer initialization
//    @NotNull
    private List<Address> addresses = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean hasId(String id) {
        return this.id == id;
    }

    @ApiModelProperty(position = 1, required = true, value = "name containing only lowercase letters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(position = 2, required = true, value = "Must not be null")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }


//    @Override
//    public String toString() {
//        final StringBuilder s = new StringBuilder();
//        s.append("Address [ street= ").append(street).append(' ').append("houseNumber = ").append(houseNumber).append(", ").append("city = ").append(city)
//                .append("zipCode = ").append(zipCode).append(']');
//        return s.toString();
//    }


    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder();
        s.append("Customer { id=").append(id).append(", name=").append(name).append(", email=").append(email).append(", addresses=").append(addresses)
                .append(" }");
        return s.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((addresses == null) ? 0 : addresses.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (addresses == null) {
            if (other.addresses != null)
                return false;
        } else if (!addresses.equals(other.addresses))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
