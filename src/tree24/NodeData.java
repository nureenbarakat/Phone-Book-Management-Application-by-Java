                 
package tree24;
import p1.Contact;
public class NodeData {
    public Contact dData; 
	public NodeData(Contact dd) // constructor
	{
		dData = dd;
	}
	public void showValue() 
	{
		System.out.println(dData);
	}

    @Override
    public String toString() {
        return dData.toString(); 
    }
}
