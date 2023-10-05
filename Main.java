public class Main{
    public static void main(String[] args){
        DoubleLinkedList ll = new DoubleLinkedList();
        ll.addAtStart(21);
        ll.addAtStart(20);
        ll.addAtStart(1);
        ll.addAtStart(0);
        ll.addAtStart(50);
        
        // DoubleLinkedList l2 = new DoubleLinkedList();
        // l2.addAtStart(50);
        // l2.addAtStart(40);
        // l2.addAtStart(30);
        // l2.addAtStart(20);
        // l2.addAtStart(10);
        
        // ll.print();
        // DoubleLinkedList l3 = mergeDoubleLinkedList(ll, l2);
        // System.out.println("The new List");
        // l3.print();
        // System.out.println("List 1");
        // ll.print();
        // System.out.println("List 2");
        // l2.print();
    }
    public static DoubleLinkedList DoubleLinkedListReverse(DoubleLinkedList lObject){
        if(lObject.head == null){
            System.out.println("List is empty");
            return lObject;
        }
        else{
            Node curr = lObject.head;
            Node nextcurr = curr.next;
            Node temp;
            while(nextcurr!=null){
                temp = nextcurr.next;
                nextcurr.next = curr;
                curr = nextcurr;
                nextcurr = temp;
            }
            lObject.head.next = null;
            lObject.head = curr;
        }
        return lObject;
    }
    public static DoubleLinkedList mergeDoubleLinkedList(DoubleLinkedList object_1,DoubleLinkedList object_2){
        DoubleLinkedList temp = clone(object_1);
        temp.tail.next = object_2.head;
        temp.tail = object_2.tail;
        return temp;
    }
    public static DoubleLinkedList clone(DoubleLinkedList object_1){
        DoubleLinkedList temp = new DoubleLinkedList();
        if(object_1.head == null){
            return temp;
        }
        Node currNode = object_1.head;
        while(currNode != null){
            temp.addAtEnd(currNode.data);
            currNode = currNode.next;
        }
        return temp;
    }
}

abstract class List {
    abstract public void print();                                                   
    abstract public void addAtStart(int element);                                
    abstract public void addAtEnd(int element);                                  
    public boolean addAtLocation(int location,int element){return false;};                
    public Node delAtEnd(){return null;};
    public Node delAtStart(){return null;};
    public Node delAtLocation(int location){return null;};
    abstract public Node search(int element);                                                                 
    abstract public void sorting(int order);                                     
    abstract public void UpdatedDoubleLinkedList(int element,int elementToFind);
    public Node removeDuplicatesFromSortedList(){return null;};
}

class Node {
    int data;
    Node next;
    Node prev;

    public Node(){
        this.data=0;
        this.next = null;
        this.prev = null;
    }

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class DoubleLinkedList extends List{
    Node head;
    int size;
    Node tail;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addAtStart(int element){            
        Node newNode = new Node(element);
        newNode.next = head;
        if (head != null)                   // Make sure that you have a condition to check if head is not null before setting its prev. 
            head.prev = newNode;            // This is because if the list is empty, then head would be null and trying to access null.prev 
                                            // would result in a NullPointerException.
        head = newNode;
        if (tail == null)
            tail=head;
        size++;
    }

    public void addAtEnd(int element){
        Node newNode = new Node(element);
        size++;
        if(head == null){
            head = newNode;
            tail = newNode;
            return;
        }
        else{
            tail.next = newNode;
            tail= newNode;
        }
        
    }

    public boolean addAtLocation(int location,int element){
        if(location>=0 &&location<=size){                       // Validation if location is valid or not
            if(location == 0)
                addAtStart(element);
            else if (location == size)
                addAtEnd(element);
            else{
                int counter = 0;
                Node curr = head;
                Node temp = new Node(element);
                while(curr.next!=null){
                    if (counter==location-1){
                        temp.next = curr.next;
                        curr.next = temp;
                        size++;
                    }
                    counter++;
                    curr = curr.next;
                }
            }
            return true;
        }
        else{
            System.out.println("Location is not Valid");
        }                        
        return false;
    }

    public void print(){
        if(head == null){
            System.out.println("List is empty");
            return;
        }
        Node currNode = head;
        while(currNode != null){
            System.out.print(currNode.data + "-> ");
            currNode = currNode.next;
        }
        System.out.println("null");
    }

    public Node delAtEnd() {
        if (head == null){
            System.out.println("List is Empty\nCannot Perform this Action");
            return null;
        }
        else if (head == tail){
            head = null;
            tail = null;
            size--;
        }
        else{
            Node curr = head;
            while(curr.next!=tail){
                curr=curr.next;}
                Node Temp = tail;
                curr.next = null;
                tail = curr;
                size--;
                return Temp;
            
        }
        return null;
    }

    public Node delAtStart(){                               
        if (head==null){
                System.out.println("List is empty");
                return null;
        }
        Node currNode = head;
        head = currNode.next;
        size--;
        return currNode;
    }

    public Node delAtLocation(int location){
        Node removed = head;
        if(location>=0 && location<size){
            if (location==0)
                delAtStart();
            else if(location==size-1)
                delAtEnd();
            else{
                int counter = 0;
                Node curr = head;
                while(curr.next!=null){
                    if (counter==location-1){
                        if(curr.next.next!=null){
                            removed = curr.next;
                            curr.next = curr.next.next;
                        }
                        else{
                            removed = curr.next;
                            curr.next = null;
                        }     
                        size--;
                    }
                    counter++;
                    curr = curr.next;
                }
            }
        }
        else{
            System.out.println("Invalid Location");
            return null;
        }
        return removed;
    }
    
    public Node search(int element){
            if (head==null){
                System.out.println("List is empty");
                return null;
            }
            Node currNode = head;
            while(currNode!=null){
                if (currNode.data == element)
                    return currNode;
                currNode = currNode.next;
            }
            return null;
    } 

    public void sorting(int order){
            Node curr = head;
            Node curr2 = head;
            Node temp = new Node();
            while(curr2!=null){
                while(curr.next!=null){
                     if ((order == 1 && curr.data > curr.next.data) || (order == 2 && curr.data < curr.next.data)) {
                        temp.data = curr.data;
                        curr.data = curr.next.data;
                        curr.next.data = temp.data;
                    }
                    curr=curr.next;
                }
                curr = head;
                curr2 = curr2.next;
            }    
    }

    public void UpdatedDoubleLinkedList(int updatedValue,int elementToFind){
        Node result = search(elementToFind);
        if (result == null){
            System.out.println("List is empty");
            return;
        }
        else{
            Node currNode = head;
            while(currNode!=null){
                if (currNode == result)
                    currNode.data = updatedValue;
                currNode = currNode.next;
            }
        }

        }
    
    public Node removeDuplicatesFromSortedList(){
        if (head==null)
            return head;
        Node curr = head;
        while(curr.next!=null){
            if (curr.data == curr.next.data){
                curr.next = curr.next.next;
            }
            else
                curr = curr.next;
        }
        return head;
    }

}