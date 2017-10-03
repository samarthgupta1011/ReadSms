package samarthgupta.com.trysms;

/**
 * Created by samarthgupta on 04/10/17.
 */

public class MessageInfo {
    String date,bankName,creDeb,accNumber;
    float amount,bal;
    long id;

    public MessageInfo(){

    }


    public MessageInfo(String date, String bankName, String creDeb, float amount, float bal,String accNumber, long id) {
        this.date = date;
        this.bankName = bankName;
        this.creDeb = creDeb;
        this.amount = amount;
        this.bal = bal;
        this.accNumber = accNumber;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCreDeb() {
        return creDeb;
    }

    public void setCreDeb(String creDeb) {
        this.creDeb = creDeb;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getBal() {
        return bal;
    }

    public void setBal(float bal) {
        this.bal = bal;
    }
}
