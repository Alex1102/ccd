package model;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//TODO check whether to import or search for alternative avoiding Pustefix-dependency
//import de.schlund.pfixcore.beans.Exclude;

/**
 * Holder for customer's data.
 *
 * @author <a href="mailto:carsten.helmstaedter@1und1.de">Carsten Helmstaedter</a>
 *
 */
public class Customer_1 implements ROCustomer {
//    private Long customerNumber;
//    private CustomerCompanyDetails companyDetails;
//    private Contact standardContact;
//    private PaymentDetails standardPaymentDetails;
//    private OptInPermission optInPermission;
//    private Map<OptInType, OptInState> existingOptIn;
//    private Boolean isCorporateCustomer;
//    private CustomerValue value;
//    private final Map<OptInTarget, OptInStatus> permissions = new HashMap<>(); // VTRACC.747 => Retrieved from PermissionService.
//
//    private Integer marketId;
//
//    public void setPermissions(Map<OptInTarget, OptInStatus> permissions) {
//        this.permissions.putAll(permissions);
//    }
//
//    @Override
//    public Map<OptInTarget, OptInStatus> getPermissions() {
//        return Collections.unmodifiableMap(permissions);
//    }
//
//    @Override
//    public boolean isCorporateCustomer() {
//        return isCorporateCustomer;
//    }
//
//    @Override
//    public Long getCustomerNumber() {
//        return customerNumber;
//    }
//
//    public void setCustomerNumber(Long customerNumber) {
//        this.customerNumber = customerNumber;
//    }
//
//    @Override
//    public CustomerCompanyDetails getCustomerCompanyDetails() {
//        return companyDetails;
//    }
//
//    public void setCompanyDetails(CustomerCompanyDetails companyDetails) {
//        this.companyDetails = companyDetails;
//    }
//
//    @Override
//    public CustomerValue getValue() {
//        return this.value;
//    }
//
//    public void setValue(CustomerValue value) {
//        this.value = value;
//    }
//
//    @Override
//    public Contact getStandardContact() {
//        return standardContact;
//    }
//
//    public void setStandardContact(Contact standardContact) {
//        this.standardContact = standardContact;
//    }
//
//    @Override
////TODO see import to-do
////    @Exclude
//    public PaymentDetails getStandardPaymentDetails() {
//        return standardPaymentDetails;
//    }
//
//    public void setStandardPaymentDetails(PaymentDetails standardPaymentDetails) {
//        this.standardPaymentDetails = standardPaymentDetails;
//    }
//
//    @Override
//    public OptInPermission getOptInPermission() {
//        return optInPermission;
//    }
//
//    public void setOptInPermission(OptInPermission optInPermission) {
//        this.optInPermission = optInPermission;
//    }
//
//    /**
//     * {@link Map} with OptIn settings of customer. Don't try to modify, you will get an unmodifiable map!
//     */
//    @Override
////TODO see import to-do
////    @Exclude
//    public Map<OptInType, OptInState> getExistingOptIn() {
//        if (existingOptIn == null) {
//            return null;
//        }
//        return Collections.unmodifiableMap(existingOptIn);
//    }
//
//    public void setExistingOptIn(Map<OptInType, OptInState> optIn) {
//        this.existingOptIn = optIn;
//    }
//
//    public void setIsCorporateCustomer(Boolean isCorporateCustomer) {
//        this.isCorporateCustomer = isCorporateCustomer;
//    }
//
//    public Integer getMarketId() {
//        return marketId;
//    }
//
//    public void setMarketId(Integer marketId) {
//        this.marketId = marketId;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(customerNumber, existingOptIn, optInPermission, standardContact, standardPaymentDetails, value);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        Customer other = (Customer) obj;
//        return Objects.equals(customerNumber, other.customerNumber) //
//                && Objects.equals(existingOptIn, other.existingOptIn) //
//                && Objects.equals(optInPermission, other.optInPermission) //
//                && Objects.equals(standardContact, other.standardContact) //
//                && Objects.equals(standardPaymentDetails, other.standardPaymentDetails) //
//                && Objects.equals(isCorporateCustomer, other.isCorporateCustomer) //
//                && Objects.equals(value, other.value);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Customer [customerNumber=");
//        builder.append(customerNumber);
//        builder.append(", isCorporateCustomer=");
//        builder.append(isCorporateCustomer);
//        builder.append(", companyDetails=");
//        builder.append(companyDetails);
//        builder.append(", standardContact=");
//        builder.append(standardContact);
//        builder.append(", standardPaymentDetails=");
//        builder.append(standardPaymentDetails);
//        builder.append(", existingOptIn=");
//        builder.append(existingOptIn);
//        builder.append(", optInPermission=");
//        builder.append(optInPermission);
//        builder.append(", value=");
//        builder.append(value);
//        builder.append("]");
//        return builder.toString();
//    }

}
