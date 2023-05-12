import p1.Contact;
public class Node {
    Contact data;
    Node left;
    Node right;
    int h;
  public Node(Contact c){
    data=c;
    left=null;
    right=null;
    }
    @Override
    public String toString() {
        return data.toString(); //To change body of generated methods, choose Tools | Templates.
    }
  
}
