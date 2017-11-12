package model;


import java.util.Date;
import java.util.Objects;

/**
 * Holder for customer's contact data. This class is immutable.Use {@link com.oneandone.sales.model.customer.Contact.Builder} to create.
 *
 * @author <a href="mailto:carsten.helmstaedter@1und1.de">Carsten Helmstaedter</a>
 *
 */
public final class Contact {
    /**
     * Builder for {@link Contact}.
     */
    public static class Builder {
        private Salutation salutation;
        private String firstName;
        private String lastName;
        private String companyName;
        private String title;
        private Date dateOfBrith;

        private String street;
        private String streetNumber;
        private String phone;
        private String zipCode;
        private String city;

        private String email;

        private String phoneCountryCode;
        private String phoneAreaCode;
        private String phoneNumber;

        public Builder salutation(Salutation bSalutation) {
            this.salutation = bSalutation;
            return this;
        }

        public Builder dateOfBirth(Date bDateOfBrith) {
            this.dateOfBrith = bDateOfBrith;
            return this;
        }

        public Builder firstName(String bFirstName) {
            this.firstName = bFirstName;
            return this;
        }

        public Builder lastName(String bLastName) {
            this.lastName = bLastName;
            return this;
        }

        public Builder companyName(String bCompanyName) {
            this.companyName = bCompanyName;
            return this;
        }

        public Builder title(String bTitle) {
            this.title = bTitle;
            return this;
        }

        public Builder street(String bStreet) {
            this.street = bStreet;
            return this;
        }

        public Builder streetNumber(String bStreetNumber) {
            this.streetNumber = bStreetNumber;
            return this;
        }

        public Builder phone(String bPhone) {
            this.phone = bPhone;
            return this;
        }

        public Builder zipCode(String bZipCode) {
            this.zipCode = bZipCode;
            return this;
        }

        public Builder city(String bCity) {
            this.city = bCity;
            return this;
        }

        public Builder email(String bEmail) {
            this.email = bEmail;
            return this;
        }

        public Builder phoneCountryCode(String bPhoneCountryCode) {
            this.phoneCountryCode = bPhoneCountryCode;
            return this;
        }

        public Builder phoneAreaCode(String bPhoneAreaCode) {
            this.phoneAreaCode = bPhoneAreaCode;
            return this;
        }

        public Builder phoneNumber(String bPhoneNumber) {
            this.phoneNumber = bPhoneNumber;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }

    private final Salutation salutation;
    private final String firstName;
    private final String lastName;
    private final String companyName;
    private final String title;

    private final String street;
    private final String streetNumber;
    private final String phone;
    private final String zipCode;
    private final String city;
    private final Date dateOfBrith;
    private final String phoneCountryCode;
    private final String phoneAreaCode;
    private final String phoneNumber;

    private final String email;

    private Contact(Builder builder) {
        this.salutation = builder.salutation;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBrith = builder.dateOfBrith;
        this.companyName = builder.companyName;
        this.title = builder.title;

        this.street = builder.street;
        this.streetNumber = builder.streetNumber;
        this.phone = builder.phone;
        this.zipCode = builder.zipCode;
        this.city = builder.city;

        this.email = builder.email;
        this.phoneAreaCode = builder.phoneAreaCode;
        this.phoneCountryCode = builder.phoneCountryCode;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getdateOfBrith() {
        return dateOfBrith;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Salutation getSalutation() {
        return salutation;
    }

    public String getTitle() {
        return title;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public boolean hasEmail() {
        return Objects.nonNull(email) && email.trim().length() > 0;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((salutation == null) ? 0 : salutation.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
        result = prime * result + ((streetNumber == null) ? 0 : streetNumber.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
        result = prime * result + ((dateOfBrith == null) ? 0 : dateOfBrith.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Contact other = (Contact) obj;
        if (!Objects.equals(city, other.city)) {
            return false;
        }
        if (!Objects.equals(companyName, other.companyName)) {
            return false;
        }
        if (!Objects.equals(email, other.email)) {
            return false;
        }
        if (!Objects.equals(firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(dateOfBrith, other.dateOfBrith)) {
            return false;
        }
        if (!Objects.equals(salutation, other.salutation)) {
            return false;
        }
        if (!Objects.equals(street, other.street)) {
            return false;
        }
        if (!Objects.equals(streetNumber, other.streetNumber)) {
            return false;
        }
        if (!Objects.equals(phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(title, other.title)) {
            return false;
        }
        if (!Objects.equals(zipCode, other.zipCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder2 = new StringBuilder();
        builder2.append("Contact [salutation=");
        builder2.append(salutation);
        builder2.append(", firstName=");
        builder2.append(firstName);
        builder2.append(", lastName=");
        builder2.append(lastName);
        builder2.append(", dateOfBrith=");
        builder2.append(dateOfBrith);
        builder2.append(", companyName=");
        builder2.append(companyName);
        builder2.append(", title=");
        builder2.append(title);
        builder2.append(", street=");
        builder2.append(street);
        builder2.append(", streetNumber=");
        builder2.append(streetNumber);
        builder2.append(", phone=");
        builder2.append(phone);
        builder2.append(", zipCode=");
        builder2.append(zipCode);
        builder2.append(", city=");
        builder2.append(city);
        builder2.append(", email=");
        builder2.append(email);
        builder2.append("]");
        return builder2.toString();
    }
}
