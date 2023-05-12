//we use minimumheap not maximum because it matches our specifications when we print the data
package Heap;

import p1.Contact;

public class MinimumHeap {
  
    private Contact[] Heap;
    private int size;
    private int maxsize;
 
    private static final int FRONT = 1;
    
    //constructor
    public MinimumHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new Contact[this.maxsize + 1];
        Heap[0] = new Contact(); //takes data from the user and add it in the root 
    }
    //equation of get parent
    private int parent(int index)
    {
        return index / 2;
    }
    //equation of get leftchild
    private int leftChild(int index)
    {
        return (2 * index);
    }
    //equation of get rightchild
    private int rightChild(int index)
    {
        return (2 * index) + 1;
    }
    //condition to check if the node is leaf or not
    private boolean isLeaf(int index)
    {
        if (index >= (size / 2)  &&  index <= size)
        { 
            return true;
        }
        return false;
    }
 
    private void swap(int fpos, int spos)
    {
        Contact temp;
        temp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = temp;
    }
 
    private void minHeapify(int index)
    {
        if (!isLeaf(index))
        { 
            if ( Heap[index].compareTo(Heap[leftChild(index)]) > 0  || Heap[index].compareTo(Heap[rightChild(index)]) > 0)
            {
                if (Heap[leftChild(index)].compareTo(Heap[rightChild(index)]) < 0)
                {
                    swap(index, leftChild(index));
                    minHeapify(leftChild(index));
                }else
                {
                    swap(index, rightChild(index));
                    minHeapify(rightChild(index));
                }
            }
        }
    }
 
    public void insert(Contact element)
    {
        Heap[++size] = element;
        int current = size;
 
        while (Heap[current].compareTo(Heap[parent(current)]) < 0)
        {
            swap(current,parent(current));
            current = parent(current);
        }	
    }
 
    public void print()
    {
        for (int i = 1; i <= size / 2; i++ )
        {
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2*i] 
                + " RIGHT CHILD :" + Heap[2 * i  + 1]);
            System.out.println();
        } 
    }
 
    public void minHeap()
    {
        for (int index = (size / 2); index >= 1 ; index--)
        {
            minHeapify(index);
        }
    }
 
    public Contact remove()
    {
        Contact popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--]; 
        minHeapify(FRONT);
        return popped;
    }
     public boolean delete(Contact C)
    {
        Contact last=Heap[size];
        int result=search2(C);
        if(result==-1)
            return false;
        
        swap(result, size);
        size--;
        minHeapify(FRONT);
        return true;
    }
    
    
    
 public Contact search(Contact c)
    {
        for(int i=0;i<size;i++){
            if(c.compareTo(Heap[i])==0)
                return Heap[i];
        }
        return null;
    }
 //for delete method
 public int search2(Contact c)
    {
        for(int i=0;i<size;i++){
            if(c.compareTo(Heap[i])==0)
                return i;
        }
        return -1;
    }
  //method to get number of nodes
  public int getSize(){
        return size;
    }
  //method to get the height
   public int height(){
       return (int)Math.log(size);
   }
}
