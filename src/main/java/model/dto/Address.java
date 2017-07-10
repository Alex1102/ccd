package model.dto;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;


@SuppressWarnings("serial")
@XmlRootElement

//@Entity : deactivated, because follow exception
//Hibernate Caused by: org.hibernate.MappingException: Could not determine type for: java.util.List
//Fix my befor activating again
public class Address implements Serializable {


    @Id
    @GeneratedValue
    private String id;

    @NotNull
    @NotEmpty
    private String street;

    @NotNull
    @NotEmpty
    private String houseNumber;

    @NotNull
    @NotEmpty
    private String zipCode;

    @NotNull
    @NotEmpty
    private String city;


    /**
     * Creates an empty {@link Address} object.
     */
    public Address() {}

    /**
     * Creates an {@link Address} object from the given parameters.
     * 
     * @param street
     * @param houseNumber
     * @param zipCode
     * @param city
     * @throws IllegalArgumentException
     *             if one of the parameters is <code>null</code>.
     */
    public Address(final String street, final String houseNumber, final String zipCode, final String city) throws IllegalArgumentException {
        setStreet(street);
        setHouseNumber(houseNumber);
        setZipCode(zipCode);
        setCity(city);
    }


    /**
     * @return the street (without house number)
     */
    public String getStreet() {
        return street;
    }

    /**
     * @return the house number
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * @return the zip code
     */
    public String getZipcode() {
        return zipCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * set the {@link Address}'s street (without house number) - can not be null
     * 
     * @param street
     * @throws IllegalArgumentException
     *             if the parameter is <code>null</code>.
     */
    public void setStreet(final String street) {
        if (street == null) {
            throw new IllegalArgumentException("street is null");
        }
        this.street = street;
    }

    /**
     * set the {@link Address}'s house number - can not be null
     * 
     * @param houseNumber
     * @throws IllegalArgumentException
     *             if the parameter is <code>null</code>.
     */
    public void setHouseNumber(final String houseNumber) {
        if (houseNumber == null) {
            throw new IllegalArgumentException("houseNumber is null");
        }
        this.houseNumber = houseNumber;
    }

    /**
     * set the {@link Address}'s zip code - can not be null
     * 
     * @param zipCode
     * @throws IllegalArgumentException
     *             if the parameter is <code>null</code>.
     */
    public void setZipCode(final String zipCode) {
        if (zipCode == null) {
            throw new IllegalArgumentException("zipCode is null");
        }
        this.zipCode = zipCode;
    }

    /**
     * set the {@link Address}'s city - can not be null
     * 
     * @param city
     * @throws IllegalArgumentException
     *             if the parameter is <code>null</code>.
     */
    public void setCity(final String city) {
        if (city == null) {
            throw new IllegalArgumentException("city is null");
        }
        this.city = city;
    }


    @Override
    public String toString() {
        final StringBuilder s = new StringBuilder();
        s.append(street).append(' ').append(houseNumber).append(", ").append(city).append(zipCode).append(' ');
        return s.toString();
    }

    @Override
    public int hashCode() {

        // MyTown1Hauptstr.4711

        final int prime = 31;
        int result = 1;
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((houseNumber == null) ? 0 : houseNumber.hashCode());
        result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
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
        Address other = (Address) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (houseNumber == null) {
            if (other.houseNumber != null)
                return false;
        } else if (!houseNumber.equals(other.houseNumber))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        if (zipCode == null) {
            if (other.zipCode != null)
                return false;
        } else if (!zipCode.equals(other.zipCode))
            return false;
        return true;
    }

}