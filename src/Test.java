
import p1.Contact;
import java.io.File;
import java.util.Scanner;
import tree24.Tree24;
import Heap.MinimumHeap;
import java.util.Date;


public class Test {

    static AvlTree t1 = new AvlTree();
    static Tree24 t2 = new Tree24();
    static MinimumHeap t3 = new MinimumHeap(1000);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
        loadDataFromCSV();
        while (true) {
            menu();
            int selection = sc.nextInt();
            int operation;
            // AVL Tree
            if (selection == 1) {
                while (true) {
                    subMenu();
                    operation = sc.nextInt();
                    // display Contact in avl
                    if(operation==1){ 
                        System.out.println("The Height = "+t1.height());
                        System.out.println("The Number of Nodes = "+t1.getTotalNumberOfNodes());

                        System.out.println("Contacts Data in AVL TREE are:");
                       
                        Date d1=new Date();
                        t1.inorderTraversal();
                        Date d2=new Date();
                        long diff=d2.getTime()-d1.getTime();
                        System.out.println("TIME = "+diff/1000.0);
                    }
                    // Insert Contact in avl
                    else  if(operation==2){ 
                        Contact cc=readContact();
                        Date d1=new Date();
                        t1.insertData(cc);
                        Date d2=new Date();
                        long diff=d2.getTime()-d1.getTime();
                        System.out.println("Contact Inserted Successfully To AVL-TREE");
                        System.out.println("TIME = "+diff/1000.0);
                    }
                    //delete Contact in avl
                    else  if(operation==3){ 
                        System.out.print("Enter First Name of the contact you want to delete: ");
                       String FirstName=sc.next();
                       System.out.print("Enter Last Name of the contact you want to delete: ");
                       String LastName=sc.next();
                       Contact cc=new Contact();
                       cc.setFirstName(FirstName);
                       cc.setLastName(LastName);
                       Date d1=new Date();
                       Contact result=t1.searchElement(cc);
                       Date d2=new Date();
                       long diff=d2.getTime()-d1.getTime();
                       System.out.println("TIME = "+diff/1000.0);
                       if(result==null){
                           System.out.println("Cannot delete This contact");
                       }
                       else
                       {
                           System.out.println("Contact Deleted Successfully");
                       }
                    }
                    else  if(operation==4){ // search Contact in avl
                        System.out.print("Enter First Name of the contact you want to search for: ");
                       String fName=sc.next();
                       System.out.print("Enter Last Name of the contact you want to search for: ");
                       String LName=sc.next();
                       Contact cc=new Contact();
                       cc.setFirstName(fName);
                       cc.setLastName(LName);
                       Date d1=new Date();
                       Contact result=t1.searchElement(cc);
                       Date d2=new Date();
                       long diff=d2.getTime()-d1.getTime();
                       System.out.println("TIME = "+diff/1000.0);
                       if(result==null){
                           System.out.println("Contact Not Found");
                       }
                       else
                       {
                           System.out.println("Contact Info");
                           System.out.println(result);
                       }
                    }else{
                        break;
                    }
                    
                }

            } else if (selection == 2) { //24-TREE
               while (true) {
                    subMenu();
                    operation = sc.nextInt();
                    if(operation==1){ // display Contact in 24-TREE
                        System.out.println("The Height = "+t2.height());
                        System.out.println("The Number of Nodes = "+t2.getSize());

                        System.out.println("Contacts Data in TWO FOUR TREE are:");
                        Date d1=new Date();
                        t2.displayTree();
                        Date d2=new Date();
                        long diff=d2.getTime()-d1.getTime();
                        System.out.println("TIME = "+diff/1000.0);
                        
                    }
                    else  if(operation==2){ // Insert Contact in 24-TREE
                        Contact cc=readContact();
                        Date d1=new Date();
                        t2.insert(cc);
                        Date d2=new Date();
                        long diff=d2.getTime()-d1.getTime();
                        System.out.println("Contact Inserted Successfully To 24-TREE");
                        System.out.println("TIME = "+diff/1000.0);
                    }
                    else  if(operation==3){ // delete Contact in 24-TREE
                        System.out.print("Enter First Name of the contact you want to delete: ");
                       String fName=sc.next();
                       System.out.print("Enter Last Name of the contact you want to delete: ");
                       String LName=sc.next();
                       Contact cc=new Contact();
                       cc.setFirstName(fName);
                       cc.setLastName(LName);                                                                                                
                       Date d1=new Date();
                       tree24.Node res=t2.delete(t2.root, cc);
                       Date d2=new Date();
                       long diff=d2.getTime()-d1.getTime();
                       System.out.println("TIME = "+diff/1000.0);
                       if(res==null){
                           System.out.println("Contact Not Found");
                       }
                       else
                       {
                           System.out.println("Contact Deleted Successfully");
                       }
                    }
                    else  if(operation==4){ // search Contact in 24-TREE
                        System.out.print("Enter First Name of the contact you want to search for: ");
                       String fName=sc.next();
                       System.out.print("Enter Last Name of the contact you want to search for: ");
                       String LName=sc.next();
                       Contact cc=new Contact();
                       cc.setFirstName(fName);
                       cc.setLastName(LName);
                       Date d1=new Date();
                      tree24.Node res= t2.find(cc);
                      Date d2=new Date();
                       long diff=d2.getTime()-d1.getTime();
                       System.out.println("TIME = "+diff/1000.0);
                       if(res==null){
                           System.out.println("Contact Not Found");
                       }
                       else
                       {
                           System.out.println("Contact Info");
                           System.out.println(res.getItem(0).toString());
                       }
                    }else{
                        break;
                    }
                    
                }
            } else if (selection == 3) { // Heap
                while (true) {
                    subMenu();
                    operation = sc.nextInt();
                    if(operation==1){ // display Contact in Heap
                       System.out.println("The Height = "+t3.height());
                        System.out.println("The Number of Nodes = "+t3.getSize());
                        System.out.println("Contacts Data in Minimum Heap are:");
                        Date d1=new Date();
                        t3.print();
                        Date d2=new Date();
                        long diff=d2.getTime()-d1.getTime();
                        System.out.println("TIME = "+diff/1000.0);
                    }
                    else  if(operation==2){ // Insert Contact in Heap
                         Contact cc=readContact();
                         Date d1=new Date();
                         t3.insert(cc);
                         Date d2=new Date();
                        long diff=d2.getTime()-d1.getTime();
                        System.out.println("TIME = "+diff/1000.0);
                        System.out.println("Contact Inserted Successfully To MinHeap-TREE"); 
                    }
                    else  if(operation==3){ // delete Contact in Heap
                         System.out.print("Enter First Name of the contact you want to Delete: ");
                       String fName=sc.next();
                       System.out.print("Enter Last Name of the contact you want to Delete: ");
                       String LName=sc.next();
                       Contact c=new Contact();
                       c.setFirstName(fName);
                       c.setLastName(LName);
                       Date d1=new Date();
                       boolean res=t3.delete(c);
                       Date d2=new Date();
                        long diff=d2.getTime()-d1.getTime();
                        System.out.println("TIME = "+diff/1000.0);
                       if(res){
                           System.out.println("Contact Deleted Successfully");
                       }
                       else
                       {
                           System.out.println("Contact NOT FOUND");
                       }
                    }
                    else  if(operation==4){ // search Contact
                        System.out.print("Enter First Name of the contact you want to search for: ");
                       String fName=sc.next();
                       System.out.print("Enter First Name of the contact you want to search for: ");
                       String LName=sc.next();
                       Contact c=new Contact();
                       c.setFirstName(fName);
                       c.setLastName(LName);
                       Date d1=new Date();
                       Contact res=t3.search(c);
                       Date d2=new Date();
                        long diff=d2.getTime()-d1.getTime();
                        System.out.println("TIME = "+diff/1000.0);
                       if(res==null){
                           System.out.println("Contact Not Found");
                       }
                       else
                       {
                           System.out.println("Contact Info");
                           System.out.println(res);
                       }
                    }else{
                        break;
                    } 
                }
            } else {
                System.out.println("Thanks");
                System.exit(0);
            }

        }

    }

    static void loadDataFromCSV() {
        try {
            Scanner sc = new Scanner(new File("ContactFile.csv"));
            while (sc.hasNext()) {
                String s = sc.nextLine();
                //x array of contacts
                String x[] = s.split(",");
                //array of name(firstname,lastname)
                String name[] = x[0].split(" ");
                String firstName = "";
                String lastName = "";
                if (name.length == 2) {
                    firstName = name[0];
                    lastName = name[1];
                } else {
                    firstName = name[0];
                    lastName = "";
                }

                Contact c = new Contact(firstName, lastName, x[1], x[2], x[3]);
                t1.insertData(c);
                t2.insert(c);
                t3.insert(c);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    static void menu() {
        System.out.println("Please Select your choice: ");
        System.out.println("1- AVL-Tree");
        System.out.println("2- 24-Tree");
        System.out.println("3- Heap-Tree");
        System.out.println("Other Key To exit");
        System.out.println("Your selection is: ");
    }

    static void subMenu() {
        System.out.println("Please Select an operation: ");
        System.out.println("1- Display Contacts");
        System.out.println("2- Add New Contact");
        System.out.println("3- Delete Contact");
        System.out.println("4- Search For Contact");
        System.out.println("Any other choice to Back Main Menu");
        System.out.println("Your selection is: ");
    }
    static Contact readContact(){
        
        Contact c=new Contact();
        System.out.println("Please Enter your First Name: ");
        c.setFirstName(sc.next());
        System.out.println("Please Enter your Last Name: ");
        c.setLastName(sc.next());
        System.out.println("Please Enter your Phone: ");
        c.setPhone(sc.next());
        System.out.println("Please Enter your Email: ");
        c.setEmail(sc.next());
        System.out.println("Please Enter your address: ");
        c.setAddress(sc.next());
     
    return c;
    }
}
