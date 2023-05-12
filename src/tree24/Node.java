
package tree24;

import p1.Contact;

public class Node {
	private static final int ORDER = 4;
	private int numItems;
	private Node parent;
	private Node childArray[] = new Node[ORDER];
	private NodeData itemArray[] = new NodeData[ORDER - 1];
	public void addChild(int childNum, Node child) {
		childArray[childNum] = child;
		if (child != null)
			child.parent = this;
	}
	public Node removeChild(int childNum) {
		Node tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}
	public Node getChild(int childNum) {
		return childArray[childNum];
	}
	public Node getParent() {
		return parent;
	}
	public boolean isLeaf() {
		return (childArray[0] == null) ;
	}

	public int getNumItems() {
		return numItems;
	}

	public void setNumItems(int theValue) {
		numItems = theValue;
	}
	public NodeData getItem(int index) 
	{
		return itemArray[index];
	}

	public NodeData setItem(int index, NodeData theValue) 
	{
		itemArray[index] = theValue;
		return itemArray[index];
	}

	public boolean isFull() {
		return (numItems == (ORDER - 1));
	}

	public int insertItem(NodeData newItem) {
		//if the node isn't full will increase the number items
		numItems++; 
		Contact newKey = newItem.dData; 
		for (int j = ORDER - 2; j >= 0; j--)
		{ 
			if (itemArray[j] == null) // if node is null go left 
				continue; 
			else //if the node is full get it's key
			{ 
				Contact itsKey = itemArray[j].dData;
				if (newKey.compareTo(itsKey) <0) 
					itemArray[j + 1] = itemArray[j]; //if bigger shift right
				else {
					itemArray[j + 1] = newItem; 
					return j + 1; 
				} 
			} 
		} 
		itemArray[0] = newItem; 
		return 0;
	} 

	public void insertatfront(NodeData newItem) {

		Contact newKey = newItem.dData; // key of new item
		numItems++;
		for (int j = numItems - 1; j > 0; j--) {
			itemArray[j] = itemArray[j - 1];
			addChild(j + 1, removeChild(j));
		}
		addChild(1, removeChild(0));
		itemArray[0] = newItem;
		addChild(0, null);
	}

	public NodeData removeItem() 
	{
		//if node is not empty
		NodeData temp = itemArray[numItems - 1]; 
		itemArray[numItems - 1] = null; 
		numItems--;
		return temp; 
	}

	public void displayNode() 
	{
		for (int j = 0; j < numItems; j++)
			itemArray[j].showValue(); 
	}

	public void displayvalue(int j) 
	{
		itemArray[j].showValue(); 
			}

	public void deletenodevalue(Contact theValue) {
		// Only for leafs
		int flag = -1;
		for (int i = 0; i < numItems; i++) {
			if ( theValue.compareTo(itemArray[i].dData) == 0) {
				flag = i;
			}
			if (flag != -1 && i + 1 < numItems) {
				itemArray[i].dData = itemArray[i + 1].dData;
			}
		}
		itemArray[numItems - 1] = null; 
		numItems--; 

	}

	public void deletevalue(Contact theValue, String side) {
		// Only for leafs
		int flag = -1;
		for (int i = 0; i < numItems; i++) {
			if (theValue.compareTo(itemArray[i].dData) == 0) {
				flag = i;
			}
			if (flag != -1 && i + 1 < numItems) {
				itemArray[i].dData = itemArray[i + 1].dData;
			}
		}
		itemArray[numItems - 1] = null; 
		numItems--; 

	}

	public Node getsibiling(Contact theValue) {
		Node x = null;
		Node p = getParent();
		if (numItems != 0) {
			for (int i = 0; i <= p.numItems; i++) {
				if (p.childArray[i].itemArray[0].dData.compareTo(theValue) <0) {
					x = p.childArray[i];
				}
			}
		} else if (numItems == 0) {
			for (int i = 0; i <= p.numItems; i++) {
				if (p.childArray[i].itemArray[0] == null) {
					
					if (i != 0) {
						x = p.childArray[i - 1];
					}
				}
			}
		}
		return x;
	}

     
}
