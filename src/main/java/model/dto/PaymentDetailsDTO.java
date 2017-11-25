package model.dto;


import javax.validation.Valid;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Data Transfer Object to store and deliver payment details data. There are
 * three main types of payment details: direct debit from a bank account,
 * payment via credit card, or charging a paypal account. Next to some general
 * information such as holder, there are different data for each of this payment
 * type: credit cards use the fields creditCardType, creditCardNumber and
 * validTo; for bank accounts are the fields bankCode and bankAccount; and for
 * paypal accounts exist the fields payPalEmail and payPalRef. Fields which are
 * not needed for a payment type usually stay null. For more information about
 * payment types and payment details, see
 */

//@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
//@SuppressWarnings("PMD.UnusedPrivateField")
public class PaymentDetailsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The primary key of the payment details tuple.
     */
    private Integer paymentDetailsId;
    /**
     * The payment dedication ID. These values are described in the class
     * PaymentDedicationConstants.
     *
     */
    private Integer paymentDedicationId;
    /**
     * The payment type of this payment details. It show which type of debit
     * transfer this payment details must be used for. The values are described
     * in the class PaymentTypeConstants.
     *
     */
    private Integer paymentTypeId;
    /**
     * The iPayment ID for this payment details. It is given by iPayment system
     * and usually only used for credit cards. It has a length of 20 characters.
     */
    private String ipaymentId;

    /**
     * The bank code name of the credit institution where the bank account is
     * maintained. It is only used for payment details with payment type
     * AUTOMATIC_DEBIT_TRANSFER.
     */
    private String bankName;

    /**
     * The bank code number of the credit institution where the bank account is
     * maintained. It is only used for payment details with payment type
     * AUTOMATIC_DEBIT_TRANSFER.
     */
    private String bankCode;

    /**
     * The iso country code of the bank. It is only used for payment details
     * with payment type AUTOMATIC_DEBIT_TRANSFER.
     */
    private String bankIsoCountryCode;

    /**
     * The bank account number of this payment details. It is only used for
     * payment details with payment type AUTOMATIC_DEBIT_TRANSFER. It has a
     * length of 50 characters.
     */
    private String bankAccount;

    /**
     * The credit card type of this payment details. It is only used for payment
     * details with payment types CREDITCARD or SWITCHCARD. The values used here
     * are described in the class CreditcardConstants.
     *
     */
    private String creditCardType;

    /**
     * The credit card number of this payment details. It is only used for
     * payment details with payment types CREDITCARD or SWITCHCARD. It has a
     * length of 50 characters.
     */
    private String creditCardNumber;

    /**
     * The email address of this payment details. It is only used for payment
     * details with payment types PAYPAL.
     */
    private String payPalEmail;

    /**
     * The paypal reference value of this payment details. It is only used for
     * payment details with payment types PAYPAL. It has a length of 30
     * characters.
     */
    private String payPalRef;

    /**
     * The holder of a bank account or credit card. It can be used for all types
     * of payment details. It has a length of 100 characters.
     */
    private String holder;

    /**
     * Additional information about this payment details. It is not mandatory
     * and independent from the payment type. It has a length of 50 characters.
     */
    private String additional;

    /**
     * Date from which this payment details is valid. It is not mandatory.
     */
    private Date validFrom;

    /**
     * Date this payment details is valid to. It mandatory for payment details
     * with payment type CREDITCARD.
     */
    private Date validTo;

    /**
     * The globals payment type of this payment details. It show which type of
     * debit transfer this payment details must be used for. The values are
     * described in the class PaymentTypeConstants from Globals2.
     */
    private Integer globalsPaymentTypeId;

    /**
     * Simple PaymentTypeId as defined by iPayment
     */
    private Integer globalsSimplePaymentTypeId;

    /**
     * The status of the paymentDetails. The IDs are described in the class
     * PaymentDetailsValidityStatusConstants.
     *
     */
    private Integer paymentDetailsValidityStatusId;

    /**
     * Timestamp representing insertion of the payment data.
     */
    private Date creationDate;

    /**
     * Contains IBAN for SEPA payment data. (This field will not be contained in
     * the database. Instead it will be enriched via iPayment.)
     */
    private String bankIBAN;

    /**
     * Contains 'Bank Identification Code'. (This field is not stored in the
     * database. Instead it will be enriched via iPayment.)
     */
    private String bankBIC;

//    /**
//     * Set containing zero or many {@link MandateDTO}s representing SEPA
//     * mandates for a specific legal entity.
//     */
//    @Valid
//    @XmlElementWrapper(name = "mandates")
//    @XmlElement(name = "mandate")
//    private Set<MandateDTO> mandates;
    /**
     * Attribute indicating the type of remaining period.
     *
     * <ol>
     *   <li>about to expire</li>
     *   <li>expired</li>
     *   <li>normal.</li>
     * </ol>
     *
     */
    private Integer paymentDetailsValidityRemainingPeriodId;
    /**
     * The paymenttarget of the payment.
     */
    private Integer paymentTarget;
    /**
     * Flag(s) for the discount.
     */
    private Integer discount;
    /**
     * Flag if the customer has net (true) or gross (false) prices.
     */
    private Boolean net;
    /**
     * Additional process flags.
     */
    private Integer processFlags;
}
