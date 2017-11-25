package model.dto;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Data Transfer Object to store and deliver customer data. It is used as an
 * input parameter for setters, as well as delivered by getters. So, to update
 * existing customers, it is possible to retrieve a DTO, set the new
 * information, and deliver the DTO to the appropriate service.
 */
//@Data
//@XmlRootElement
//@CustomerValidation
@SuppressWarnings("PMD.UnusedPrivateField")
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The internal customer number of the customer. This number serves as the
     * reference to a customer from outside the CCD system. It is equivalent to
     * the KUNDE_ID in database SPAM. For more details concerning customer
     * numbers, see
     * #Prim_rschl_ssel_von_Kunden
     */
    private String intCustomerNumber;
    /**
     * The external customer number of the customer. External numbers are
     * usually thr customer numbers or primary keys from external systems and
     * databases a customer might have been migrated into CCD. E.g. former
     * Freenet CIDs (the customer number in Freenet systems) have been migrated
     * into the Field extCustomerNumber during the Freenet import process.
     */
    private String extCustomerNumber;
    /**
     * The ID of the market / mandator the customer belongs to. These IDs are
     * described in the class MarketConstants.
     *
     */
    @NotNull
    private Integer marketId;
    /**
     * The customer type of the customer. This field differs between personal
     * and corporate customers. The IDs are descriped in the class
     * CustomerTypeConstants.
     *
     */
    @NotNull
    private Integer customerTypeId;
    /**
     * The dispatch type of the customer. This field serves as a default for how
     * to dispatch communications to the customer. Currently, only the
     * communication channels Mail and Letter are supported. The IDs are
     * descriped in the class DispatchTypeConstants.
     *
     */
    @NotNull
    private Integer dispatchTypeId;
    /**
     * The payment sort of the customer. For direct debit as the payment method,
     * there exists one payment sort type called DEBIT_TRANSFER. If this is set,
     * the customer must have a standard payment details. For other payment
     * sorts such as Cash On Advance or Delivery On Invoice, the customer must
     * not have a standard payment details, but may have additional payment
     * details. For more informations about payment methods in general and
     * payment details, see out wiki documentation at
     * Zahlungsarten_und_Bankverbindung
     *
     * @deprecated the paymentSortId will be replaced by globalsPaymentTypeId in the future.
     */
    @Deprecated
    private Integer paymentSortId;
    /**
     * The registration time of the customer. That is the date and time when the
     * customer has been created in the CCD.
     */
    private Date registrationTime;
    /**
     * The status of the customer. The IDs are described in the class
     * CustomerValidityStatusConstants.
     *
     */
    private Integer customerValidityStatusId;
    /**
     * The currency code of the customer.
     */
    private String isoCurrencyCode;
    /**
     * Id with the tax classification id.
     */
    private Integer taxClassificationId;

    /**
     * date the privacy lock was set for the customer
     */
    private Date privacyLockFrom;


}
