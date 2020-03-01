public class Iterator<T> {

    private LinkedList<T> myList;
    private LinkedList<T>.Node<T> myCurrentNode;
    private LinkedList<T>.Node<T> myPreviousNode;

    public Iterator(LinkedList<T> list) {
        myList = list;
        myCurrentNode =  myList.itsFirstNode;
        myPreviousNode = myList.itsLastNode;
    }

    public void setMyCurrentNode(LinkedList<T>.Node<T> current){
        myCurrentNode = current;
    }

    // return true if there is a "next" element, otherwise returns false
    public boolean hasNext() {
        if (myCurrentNode != null)
            return true;
        return false;
    }
    
    // return true if there is a "prior" element, otherwise returns false
    public boolean hasPrior() {
        if (myPreviousNode != null)
            return true;
        return false;
        
    }
    
    // returns the "next" node (really the current one) and
    // moves the Iterator forward by one node
    public T next() {
        T data = myCurrentNode.getData();
        myCurrentNode = myCurrentNode.getNextNode();
        return data;
    }
    
    // returns the "prior" node (really the current one) and
    // moves the Iterator backward by one node
    public T prior() {
        T prior = myPreviousNode.getData();
        myPreviousNode = myPreviousNode.getPriorNode();
        return prior;
        }
    

    // Sets this iterator to point to the last Node in the list
    public void setToEnd() {
        myCurrentNode = myList.itsLastNode;
  }
    
}