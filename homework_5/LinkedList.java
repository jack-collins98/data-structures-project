public class LinkedList<T>  {


    Node<T> itsFirstNode;
    Node<T> itsLastNode;
    private int size;


    public LinkedList() {
        itsFirstNode = null;
        itsLastNode = null;
        size = 0;
    }

    public int size() {
        return this.size;
    }
    
    public Iterator<T> getIterator() {
        return new Iterator<T>(this);
    }

    public void add(T element) {

        Node<T> node = new Node<T>(element);

        if (itsFirstNode == null) {
            itsFirstNode = node;
            itsLastNode = node;
        }
        else {
            itsLastNode.setNextNode(node);
            node.setPriorNode(itsLastNode);
            node.setNextNode(null);
            itsLastNode = node;
        }
        size++;
    }

    // THIS WILL NEED TO BE MODIFIED FOR DOUBLY LINKED LIST
    public void add(T element, int index) {
        int counter = 0;
        Node<T> next = null;
        Node<T> newNode = new Node<T>(element);
        Node<T> current = itsFirstNode;
        while (current != null ) {
            if (counter == index - 1 )
                break;
            current = current.getNextNode();
            counter++;
        }
        next = current.getNextNode();
        next.setPriorNode(newNode);
        newNode.setNextNode(current.getNextNode());
        current.setNextNode(newNode);
        newNode.setPriorNode(current);
        size++;
    }

    public T get(int index) {
        int counter = 0;
        Node<T> current = itsFirstNode;
        while (current != null ) {
            if (counter == index)
                break;
            current = current.getNextNode();
            counter++;
        }
        return current.getData();
    }

    // returns true if element is in the list, false if not
    public boolean contains(T element) {
        Node<T> pointer = itsFirstNode;
        boolean res = false;

        if (itsFirstNode == null)
            return res;
        else {
            while (pointer != null) {
                if(pointer.getData() == element) {
                    res = true;
                    break;
                }
                pointer = pointer.getNextNode(); //assign pointer of head to head.
            }
        }
        return res;
    }

   // returns the index of the element if it is in the list, -1 if not found
    public int indexOf(T element) {
       Node<T> head = itsFirstNode;
        int index = 0;
        if(itsFirstNode == null)
            return -1;
        else {
            while (head != null) {
                if (head.getData() == element) {
                    break;
                }
                else {
                head = head.getNextNode(); //assigns the next of head to head.
                if (head == null) {
                    return -1; 
                }
                index++;
                }   
            }
        }
        return index;
    }

    // returns an Iterator at the location of the element if it is in the list
    // returns the null reference if the element is not found
    public Iterator<T> iteratorAt(T element) {
        LinkedList<T> temp = new LinkedList<T>(); 
        
        Node<T> myCurrentNode = temp.itsFirstNode;
        Iterator<T> iter = temp.getIterator();
        if (iter.hasNext() == false) {
            return null; 
        } 
        else {
            while (iter.hasNext()) {
                if (myCurrentNode.getData() == element) {
                    break;
                }
                else {
                    myCurrentNode.setNextNode(myCurrentNode.getNextNode());
                    if (iter.hasNext() == false) {
                        return null;
                    }
                }
            }
        }
        return iter; 
     }
        
    public String toString() {
        String returnVal = "";
        Node<T> current = itsFirstNode;
        if (size != 0 ) {
            while (current != null ) {
                returnVal += current.toString();
                returnVal += "\n";
                current = current.getNextNode();
            }
        }
        return returnVal;
    }  // end toString

    class Node<T> {
    
        private T data;
        private Node<T> itsNext;
        private Node<T> itsPrior;
    
        public Node(T data) {
            itsNext = null;
            itsPrior = null;
            this.data = data;
        }
    
        public T getData() {
            return this.data;
        }
    
        public Node<T> getNextNode() {
            return itsNext;
        }
        
        public Node<T> getPriorNode() {
            return itsPrior;
        }
        
        public void setNextNode(Node<T> next) {
            itsNext = next;
        }
        
        public void setPriorNode(Node<T> prior) {
            itsPrior = prior;
        }
        
        public String toString() {
            return data.toString();
        }
    
    }  // end of Node<T>
}
