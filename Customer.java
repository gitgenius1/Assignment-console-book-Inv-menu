public class Customer {
    int customerID;
    String firstName;
    String secondName;
    String houseNo;
    String addressLine1;
    String addressLine2;
    String country;
    String postCode;

    public Customer(int customerID,String firstName,String secondName, String houseNo,String addressLine1,String addressLine2,String country,String postCode){

        this.customerID = customerID;
        this.firstName = firstName; 
        this.secondName = secondName; 
        this.houseNo = houseNo; 
        this.addressLine1 = addressLine1; 
        this.addressLine2 = addressLine2; 
        this.country = country; 
        this.postCode = postCode;
        }


    // overide default toString
    public String toString() {
        return "(Customer ID: " + customerID + ")" + "[First Name: " + firstName + "]" + "[secondName: " + secondName + "]" + "[houseNo: " + houseNo + "]" + "[addressLine1: " + addressLine1 + "]" + " [addressLine2: " + addressLine2 +"]" + " [country: " + country + "]" + "[postCode: " + postCode + "]";
    }

    // Setters and Getters

    public void setCustomerID(int customerID){ 
        this.customerID = customerID; 
    }

    public int getCustomerID(){
        return this.customerID;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setSecondName(String secondName){
        this.secondName = secondName; 
    }

    public String getSecondName(){
        return this.secondName; 
    }

    public void setHouseNo(String houseNo){
        this.houseNo = houseNo; 
    }
    public String getHouseNo(){
        return this.houseNo; 
    }
    public void setAddressLine1(String addressLine1){
        this.addressLine1 = addressLine1; 
    }
    public String getAddressLine1(){
        return this.addressLine1; 
    }
    public void setAddressLine2(String addressLine2){
        this.addressLine2 = addressLine2; 
    }
    public String getAddressLine2(){ 
        return this.addressLine2; 
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getCountry(){
        return this.country;
    }
    public void setPostcode(String postCode){
        this.postCode = postCode; 
    }
    public String getPostcode(){
         return this.postCode;
    }
    
}