import p1.Contact;

public class AvlTree {
    public Node rootNode;       
    public AvlTree()  
    {  
        rootNode = null;  
    }  
    //to make avl tree empty 
    public void destroyTree()  
    {  
        rootNode = null;  
    }  
     public boolean isEmpty()  
    {  
       return (rootNode == null) ;       
    }  
    public void insertData(Contact element)  
    {  
        rootNode = insertElement(element, rootNode);  
    } 
    public int height(){
        return Height(rootNode);
    }
    private int Height(Node node )  
    {  
        if(node == null)
            return -1;
        else
            return Math.max(Height(node.left), Height(node.right))+1;
    }  
    
    
    private int getHeight(Node node )  
    {  
        if(node == null)
            return -1;
        else
            return node.h;
    }  
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight)  
    {  
        if(leftNodeHeight > rightNodeHeight)
            return leftNodeHeight;
        else
            return rightNodeHeight;
     }     
    private Node insertElement(Contact element, Node node)  
    {  
        if (node == null)  
            node = new Node(element);  
        else if (element.compareTo(node.data) <0)  
        {  
            node.left = insertElement( element, node.left );  
            if( getHeight( node.left ) - getHeight( node.right ) == 2 )  
                if( element.compareTo(node.left.data) <0   )  
                    //left left
                    node = rotateWithLeftChild( node );  
                else  
                    //right left
                    node = doubleWithLeftChild( node );  
        }  
        else if( element.compareTo(node.data)>0 )  
        {  
            node.right = insertElement( element, node.right );  
            if( getHeight( node.right ) - getHeight( node.left ) == 2 )  
                if(element.compareTo(node.right.data)>0)  
                    //right right
                    node = rotateWithRightChild( node );  
                else  
                    //left right
                    node = doubleWithRightChild( node );  
        } 
        //both are equal
        else  
        node.h = getMaxHeight( getHeight( node.left ), getHeight( node.right ) ) + 1;  
        return node;  
          
    }  
    private Node rotateWithLeftChild(Node node2)  
    {  
        Node node1 = node2.left;  
        node2.left = node1.right;  
        node1.right = node2;  
        node2.h = getMaxHeight( getHeight( node2.left ), getHeight( node2.right ) ) + 1;  
        node1.h = getMaxHeight( getHeight( node1.left ), node2.h ) + 1;  
        return node1;  
    }  
    private Node rotateWithRightChild(Node node1)  
    {  
        Node node2 = node1.right;  
        node1.right = node2.left;  
        node2.left = node1;  
        node1.h = getMaxHeight( getHeight( node1.left ), getHeight( node1.right ) ) + 1;  
        node2.h = getMaxHeight( getHeight( node2.right ), node1.h ) + 1;  
        return node2;  
    }  
    private Node doubleWithLeftChild(Node node3)  
    {  
        node3.left = rotateWithRightChild( node3.left );  
        return rotateWithLeftChild( node3 );  
    }  
    private Node doubleWithRightChild(Node node1)  
    {  
        node1.right = rotateWithLeftChild( node1.right );  
        return rotateWithRightChild( node1 );  
    }      
    public int getTotalNumberOfNodes()  
    {  
        return getTotalNumberOfNodes(rootNode);  
    }  
    private int getTotalNumberOfNodes(Node head)  
    {  
        if (head == null)  
            return 0;  
        else  
        {  
            int length = 1;  
            length = length + getTotalNumberOfNodes(head.left);  
            length = length + getTotalNumberOfNodes(head.right);  
            return length;  
        }  
    }  
  
    public Contact searchElement(Contact element)  
    {  
        return searchElement(rootNode, element);  
    }  
  
    private Contact searchElement(Node head, Contact element)  
    {  
        Contact check = null;  
        while ((head != null) && check!=null)  
        {  
            Contact headElement = head.data;  
            if (element.compareTo(headElement)<0)  
                head = head.left;  
            else if (element.compareTo(headElement)>0)  
                head = head.right;  
            else  
            {  
                check = head.data; 
                break;  
            }  
            check = searchElement(head, element);  
        }  
        return check;  
    }  
    public void inorderTraversal()  
    {  
        inorderTraversal(rootNode);  
    }  
    private void inorderTraversal(Node head)  
    {  
        if (head != null)  
        {  
            inorderTraversal(head.left);  
            System.out.print(head.data+"\n");  
            inorderTraversal(head.right);  
        }  
    }  
      public void preorderTraversal()  
    {  
        preorderTraversal(rootNode);  
    }  
    private void preorderTraversal(Node head)  
    {  
        if (head != null)  
        {  
            System.out.print(head.data+"\n");  
            preorderTraversal(head.left);               
            preorderTraversal(head.right);  
        }  
    }  
      
    public void postorderTraversal()  
    {  
        postorderTraversal(rootNode);  
    }  
      
    private void postorderTraversal(Node head)  
    {  
        if (head != null)  
        {  
            postorderTraversal(head.left);               
            postorderTraversal(head.right);  
            System.out.print(head.data+"\n");  
        }  
    } 
    public Node minValueNode(Node node)
	{
	    Node temp;
        for(temp=node;temp.left!=null;temp=temp.left);

		return temp;
	}
    public int getBalance(Node N)
	{
		if (N == null)
			return 0;
		return Height(N.left) - Height(N.right);
	}
    public Node deleteNode(Node rootNode, Contact data)
	{
		if (rootNode == null)
			return rootNode;

		if (data.compareTo(rootNode.data) < 0)
			rootNode.left = deleteNode(rootNode.left, data);

		else if (data.compareTo(rootNode.data) > 0)
			rootNode.right = deleteNode(rootNode.right, data);

		else
		{

			if ((rootNode.left == null) || (rootNode.right == null))
			{
				Node temp = null;
				if (temp == rootNode.left)
					temp = rootNode.right;
				else
					temp = rootNode.left;

				if (temp == null)
				{
					temp = rootNode;
					rootNode = null;
				}
				else 
					rootNode = temp;
			}
			else
			{

				Node temp = minValueNode(rootNode.right);

				rootNode.data = temp.data;

				rootNode.right = deleteNode(rootNode.right, temp.data);
			}
		}

		if (rootNode == null)
			return rootNode;

		rootNode.h = Math.max(Height(rootNode.left), Height(rootNode.right))+1;
    	        int balance = getBalance(rootNode);

		if (balance > 1 && getBalance(rootNode.left) >= 0)
			return rotateWithRightChild(rootNode);

		if (balance > 1 && getBalance(rootNode.left) < 0)
		{
			rootNode.left = rotateWithLeftChild(rootNode.left);
			return rotateWithRightChild(rootNode);
		}

		if (balance < -1 && getBalance(rootNode.right) <= 0)
			return rotateWithLeftChild(rootNode);

		if (balance < -1 && getBalance(rootNode.right) > 0)
		{
			rootNode.right = rotateWithRightChild(rootNode.right);
			return rotateWithLeftChild(rootNode);
		}

		return rootNode;
	}
}
