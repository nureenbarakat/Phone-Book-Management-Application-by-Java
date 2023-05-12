package p1;


import java.util.Objects;


public class Contact implements Comparable<Object>{
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;

    public Contact(String firstName,String lastName, String phone, String email, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Contact() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" + "Name=" + firstName + " " + lastName + ", phone=" + phone + ", email=" + email + ", address=" + address + '}';
    }

    

    @Override
    public int compareTo(Object t) {
      String fullName=firstName+lastName;
       String otherFullName= ((Contact)t).getFirstName()+((Contact)t).getLastName();
       return fullName.compareTo(otherFullName);
    }
     
}
