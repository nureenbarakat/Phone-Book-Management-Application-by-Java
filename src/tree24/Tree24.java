
package tree24;

import p1.Contact;

public class Tree24 {
    public Node root = new Node(); 
    
	public void insert(Contact dValue)
	{
		Node curNode = root;
		NodeData tempItem = new NodeData(dValue);
		while (true) {
			if (curNode.isFull()) 
			{
				split(curNode); 
				curNode = curNode.getParent(); 
				curNode = getNextChild(curNode, dValue);
			} 

			else if (curNode.isLeaf()) 
				break; //insert when break
			// node is not full and not a leaf then go to lower level
			else
				curNode = getNextChild(curNode, dValue);
		} 

		curNode.insertItem(tempItem); 
	} 

	public void split(Node thisNode) 
	{
		// if node is full
		NodeData itemB, itemC;
		Node parent, child2, child3;
		int itemIndex;

		itemC = thisNode.removeItem(); 
		itemB = thisNode.removeItem(); 
		child2 = thisNode.removeChild(2); 
		child3 = thisNode.removeChild(3); 

		Node newRight = new Node();

		if (thisNode == root) 
		{
			root = new Node(); 
			parent = root; 
			root.addChild(0, thisNode); 
		} else
			// node is not the root
			parent = thisNode.getParent(); 

		
		itemIndex = parent.insertItem(itemB); 
		int n = parent.getNumItems(); 

		for (int j = n - 1; j > itemIndex; j--) // move parent and its children
		{ 
			Node temp = parent.removeChild(j);
			parent.addChild(j + 1, temp); 
		}
		// connect newRight to parent
		parent.addChild(itemIndex + 1, newRight);

		
		newRight.insertItem(itemC); 
		newRight.addChild(0, child2); 
		newRight.addChild(1, child3); 
	} 

	// gets appropriate child of node
	public Node getNextChild(Node theNode, Contact theValue) {
		int j;
		int numItems = theNode.getNumItems();
		for (j = 0; j < numItems; j++) 
		{ 
			if (theValue.compareTo(theNode.getItem(j).dData) <0)
				return theNode.getChild(j); 
		} 
		return theNode.getChild(j); 
	}

	public void displayTree() {
            inorderdisplay(root, 0, 0);
	}

	
	public void inorderdisplay(Node thisNode, int level, int childNumber) {
		int numItems = thisNode.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node nextNode = thisNode.getChild(j);
			if (nextNode != null)
				inorderdisplay(nextNode, level + 1, j);
			else {
				thisNode.displayNode();
				return;
			}
			if (j < thisNode.getNumItems())
				thisNode.displayvalue(j);
		}
	}

	public Node find(Contact theValue) {
		return findvalue(root, theValue);
	}

	public Node findvalue(Node theNode, Contact theValue) {

		Node l = null;
		
		int numItems = theNode.getNumItems();
		
		for (int j = 0; j < numItems; j++) 
		{ 
			if (theValue.compareTo(theNode.getItem(j).dData) ==0) {
				l = theNode;
				break;
			} else if (theValue.compareTo(theNode.getItem(j).dData) < 0 && !theNode.isLeaf()) {
				l = findvalue(theNode.getChild(j), theValue); // left child
																
				break;
 			} else if (theValue.compareTo(theNode.getItem(j).dData) > 0 && !theNode.isLeaf()) {
				l = findvalue(theNode.getChild(j + 1), theValue); //right child

			}
		}  
		return l;
	}

	public Node delete(Node currnode, Contact theValue) {
		Node y = null;
		
		if (currnode.isLeaf()) {
			if (currnode.getNumItems() > 1) {
				currnode.deletenodevalue(theValue);
				return currnode;
			} else {
				y = deleteleaf_cases(currnode, theValue);
				return y;
			}
		} else {
			
		
			Node n = getNextChild(currnode, theValue);
			Node c = getinordernode(n);
			NodeData d = c.getItem(0);
			Contact k = d.dData;
			delete(c, d.dData);
					
			Node found = find(theValue);
			for(int i = 0; i < found.getNumItems();i++){
				if(found.getItem(i).dData==theValue){
					found.getItem(i).dData=k;
				}
			}
			return found;
			
		}
	}

	public Node deleteleaf_cases(Node thisNode, Contact theValue) {
		String sibling_side = "l";
		Node p = thisNode.getParent();
		Node sibling = thisNode.getsibiling(theValue);
		if (sibling == null) {
			sibling_side = "r";
			sibling = p.getChild(1);
		}

		if (sibling.getNumItems() == 1) {
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {


					thisNode.setItem(thisNode.getNumItems() - 1, null);
					thisNode.setNumItems(thisNode.getNumItems() - 1);
					NodeData d = p.getItem(i);
					sibling.insertItem(d);
					p.removeChild(i + 1);
					for (int j = i; j < p.getNumItems(); j++) {
						if (j + 1 < p.getNumItems()) {
							p.setItem(j, p.getItem(j + 1));
							if (j + 2 <= p.getNumItems()) {
								p.addChild(j + 1, p.removeChild(j + 2));
							}
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					// Check if parent is null
					if (p.getNumItems() == 0) {
						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}

					return p;

				} else if (p.getChild(i) == sibling && sibling_side == "r") {


					thisNode.setItem(thisNode.getNumItems() - 1, null);
					thisNode.setNumItems(thisNode.getNumItems() - 1);
					NodeData d = p.getItem(i - 1);
					sibling.insertItem(d);
					p.removeChild(0);
					p.addChild(0, p.removeChild(1));

					for (int j = i; j < p.getNumItems(); j++) {
						p.setItem(j - 1, p.getItem(j));
						if (j + 1 <= p.getNumItems()) {
							p.addChild(j, p.removeChild(j + 1));
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					// Check if parent is null
					if (p.getNumItems() == 0) {
						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;

				}
			}
		} else if (sibling.getNumItems() > 1) {
			int f = 0;
			if (sibling_side == "r") {
				f = 0;
			} else {
				f = sibling.getNumItems() - 1;
			}

			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {
					thisNode.getItem(0).dData = p.getItem(i).dData;
					p.getItem(i).dData = sibling.getItem(f).dData;
					sibling.deletenodevalue(sibling.getItem(f).dData);
					return p;
				}

				if (p.getChild(i) == sibling && sibling_side == "r") {
					thisNode.getItem(0).dData = p.getItem(i - 1).dData;
					p.getItem(i - 1).dData = sibling.getItem(f).dData;
					sibling.deletenodevalue(sibling.getItem(f).dData);
					return p;
				}
			}
		}

		return null;
	}

	public Node balancetree(Node currnode) { 
		String sibling_side = "l";
		Node p = currnode.getParent();
		Node sibling = currnode.getsibiling(null);   
		if (sibling == null) {
			sibling_side = "r";
			sibling = p.getChild(1);
		}

		if (sibling.getNumItems() == 1) {
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {

					NodeData d = p.getItem(i);
					sibling.insertItem(d);
					
					sibling.addChild(sibling.getNumItems(),
							currnode.removeChild(0));
					p.removeChild(i + 1);
					for (int j = i; j < p.getNumItems(); j++) {
						if (j + 1 < p.getNumItems()) {
							p.setItem(j, p.getItem(j + 1));
							if (j + 2 <= p.getNumItems()) {
								p.addChild(j + 1, p.removeChild(j + 2));
							}
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					
					if (p.getNumItems() == 0) {

						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;
				}

				else if (p.getChild(i) == sibling && sibling_side == "r") {

					NodeData d = p.getItem(i - 1);
					sibling.insertatfront(d);
					sibling.addChild(0, currnode.removeChild(0));
					p.removeChild(0);
					p.addChild(0, p.removeChild(1));

					for (int j = i; j < p.getNumItems(); j++) {
						p.setItem(j - 1, p.getItem(j));
						if (j + 1 <= p.getNumItems()) {
							p.addChild(j, p.removeChild(j + 1));
						}
					}
					p.setItem(p.getNumItems() - 1, null);
					p.setNumItems(p.getNumItems() - 1);

					
					if (p.getNumItems() == 0) {

						if (p != root) {
							p = balancetree(p);
						} else {
							root = sibling;
						}
					}
					return p;
				}

			}

		} else if (sibling.getNumItems() > 1) {
			int f = 0;
			if (sibling_side == "r") {
				f = 0;
			} else {
				f = sibling.getNumItems() - 1;
			}
			for (int i = 0; i <= p.getNumItems(); i++) {
				if (p.getChild(i) == sibling && sibling_side == "l") {

					currnode.setNumItems(currnode.getNumItems()+1);
					currnode.addChild(1, currnode.removeChild(0));
					currnode.addChild(0,
							sibling.removeChild(sibling.getNumItems()));
					currnode.setItem(0, p.getItem(i));
					p.setItem(i, sibling.getItem(f));
					sibling.setItem(sibling.getNumItems() - 1, null);
					sibling.setNumItems(sibling.getNumItems() - 1);
					return p;
				}

				if (p.getChild(i) == sibling && sibling_side == "r") {

					currnode.setNumItems(currnode.getNumItems()+1);
					currnode.setItem(0, p.getItem(i - 1));

					p.setItem(i - 1, sibling.getItem(f));

					currnode.addChild(1, sibling.removeChild(f));


					for (int j = 0; j < sibling.getNumItems(); j++) {
						if (j + 1 < sibling.getNumItems()) {
							sibling.setItem(j, sibling.getItem(j + 1));
						}
						sibling.addChild(j, sibling.removeChild(j + 1));
					}
			sibling.setItem(sibling.getNumItems() - 1, null);
					sibling.setNumItems(sibling.getNumItems() - 1);
					
					return p;
				}
			}
		}
		return null;
	}
	
	
	
	public Node getinordernode(Node thisNode){
		Node c = null;
		if(thisNode.isLeaf()){
			c = thisNode;
		}
		else{
			c = getinordernode(thisNode.getChild(0));
		}
		return c;
	}
    public int getSize(){
        return getSize(this.root);
    } 
    public int getSize(Node node){
        if(node==null)
            return 0;
        else
            return 1+getSize(node.getChild(0))+getSize(node.getChild(1))+getSize(node.getChild(2));
    }    
    
    

        public int height(){
        return Height(this.root);
    }
    private int Height(Node node )  
    {  
        if(node == null)
            return -1;
        else
            return Math.max(Math.max(Height(node.getChild(0)),Height(node.getChild(1))), Height(node.getChild(2)))+1;
        
    }  
        

}
