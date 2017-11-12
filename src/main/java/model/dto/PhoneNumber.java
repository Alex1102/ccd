package model.dto;


import java.io.Serializable;

/**
 * Contains the elements of a phonenumber:
 * <ul>
 * <li>CC = Country Code</li>
 * <li>NDC = National Destination Code</li>
 * <li>SN = Subscriber Number</li>
 * </ul>
 * @see <a href="http://en.wikipedia.org/wiki/MSISDN#MSISDN_Format">MSISDN_Format</a>
 * @author stfl
 */
public class PhoneNumber implements Serializable {

    // use com.oneandone.access.mobile.domain.MobilePhoneNumber if needed for number parsing

    private static final long serialVersionUID = 1L;

    private static final String COUNTRY_CODE_GERMANY = "49";
    private String cc;
    private String ndc;
    private String sn;

    /**
     * create an empty {@link PhoneNumber} instance
     */
    public PhoneNumber() {}

    /**
     * create a {@link PhoneNumber} instance with the given values
     * 
     * @param cc country code
     * @param ndc national destination code
     * @param sn subscriber number
     */
    public PhoneNumber(String cc, String ndc, String sn) {
        this.cc = cc;
        this.ndc = ndc;
        this.sn = sn;
    }

    /**
     * create a {@link PhoneNumber} instance with the given values and the default (german) country code
     * 
     * @param ndc national destination code
     * @param sn subscriber number
     */
    public PhoneNumber(String ndc, String sn) {
        this(COUNTRY_CODE_GERMANY, ndc, sn);
    }

    /**
     * @return the country code
     */
    public String getCc() {
        return cc;
    }
    
    /**
     * set the country code
     * 
     * @param cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }


    /**
     * @return the national destination code
     */
    public String getNdc() {
        return ndc;
    }
    
    /**
     * set the national destination code
     * 
     * @param ndc
     */
    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    /**
     * @return the subscriber number
     */
    public String getSn() {
        return sn;
    }
    
    /**
     * set the subscriber number
     * 
     * @param sn
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PhoneNumber [cc=").append(cc).append(", ndc=").append(ndc).append(", sn=").append(sn)
                .append("]");
        return builder.toString();
    }

}
