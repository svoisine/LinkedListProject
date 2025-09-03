/*
 *  Class SortedLinkedList
 * 
 *  This class creates SortedLinkedList Object as well
 *  as creates methods that can be called on the lists.
 * 
 *  Name: Seth Voisine
 *  UGA ID: 81196637
 *  Date: 09/02/2025
 */
public class SortedLinkedList {
    private NodeType head;
    private NodeType currentPos;

    /*
     * The method constructs the list object.
     * 
     * param - none
     * 
     * @return - none
     */
    public SortedLinkedList() {
        this.head = null;
        this.currentPos = null;
    }

    /*
     * This method finds the length of the list.
     * 
     * param - none
     * 
     * @return - int length
     */
    public int getLength() {
        // base case of len of 0
        if (head == null) {
            return 0;
        }
        NodeType curr = head;
        // find the length of the list
        int len = 0;
        while (curr != null) {
            len += 1;
            curr = curr.next;
        }

        return len;
    }

    /*
     * This method inserts the item into the list while
     * maintaining the sorted order.
     * 
     * param - ItemType item
     * 
     * @return - void
     */
    public void insertItem(ItemType item) {

        // if list is empty
        if (head == null) {
            head = new NodeType();
            head.info = item;
            return;
        }

        // first value is a duplicate or insert first value
        if (item.getValue() == head.info.getValue()) {
            System.err.println("Item Already Exists");
            return;
        } else if (item.getValue() < head.info.getValue()) {
            NodeType holder = new NodeType();
            holder.info = item;
            holder.next = head;
            head = holder;
            return;
        }

        NodeType prev = head;
        NodeType currNode = head.next;

        // find insert point
        while (currNode != null && item.getValue() > currNode.info.getValue()) {
            prev = currNode;
            currNode = currNode.next;
        }

        // check duplicate at insert point
        if (currNode != null && item.getValue() == currNode.info.getValue()) {
            System.err.println("Item Already Exists");
            return;
        }

        // insert
        NodeType holder = new NodeType();
        holder.info = item;
        holder.next = currNode;
        prev.next = holder;

    }

    /*
     * This method deletes item from the linked list
     * while maintaing the original sorted order.
     * 
     * param - ItemType item
     * 
     * @return - void
     */
    public void deleteItem(ItemType item) {

        // if list is empty
        if (head == null) {
            System.out.println("You cannot delete from an empty list");
            return;
        }

        // if deleting the first value ; if it's just one node head = head.next will
        // still work since head.next is null;
        if (item.getValue() == head.info.getValue()) {
            head = head.next;
            return;
        }

        NodeType prev = head;
        NodeType currNode = head.next;

        // find deletion poiont point
        while (currNode != null && item.getValue() != currNode.info.getValue()) {
            prev = currNode;
            currNode = currNode.next;
        }
        System.out.println("Original list : " + this);
        // check duplicate at insert point
        if (currNode == null) {
            System.out.println("The item is not present in the list");
            System.out.println("New list : " + this);
            return;
        }

        prev.next = currNode.next;
        System.out.println("New list : " + this);

    }

    /*
     * This method searches the list for the location of
     * item and returns the index of the item in the list.
     * 
     * param - ItemType item
     * 
     * @return - int index
     */
    public int searchItem(ItemType item) {
        int index = 1;
        NodeType currNode = head;

        if (currNode == null) {
            return -1;
        }
        // this loop also handles the base case of an empty list
        while (currNode != null) {
            if (item.getValue() == currNode.info.getValue()) {
                return index; // return the index when we find it
            }
            currNode = currNode.next;
            index++;
        }

        System.out.println("Item is not present in the list");
        return 0; // return -1 since we did not find the item
    }

    /*
     * This method gets the next item in the list and returns
     * the number that the item is. If it gets to the end it
     * resets back to the beginning.
     * 
     * param - none
     * 
     * @return - ItemType currentPos.next
     */
    public ItemType getNextItem() {
        // case when list is empty
        if (head == null) {
            System.out.println("List is empty");
            return null;
        }

        // move currentPos up one but check to make sure you're not already at the end
        if (currentPos == null) {
            currentPos = head;
        } else {
            if (currentPos.next == null) {
                currentPos = head;
            } else {
                currentPos = currentPos.next;
            }
        }

        return currentPos.info;
    }

    /*
     * Resets currentPos back to the first item in the list.
     * 
     * param - none
     * 
     * @return - void
     */
    public void resetList() {
        currentPos = null;
    }

    /*
     * This method takes the secondList and then merges
     * both the original list and the secondList together.
     * If two items are the same it just takes one of the items.
     * This method maintains a sorted order and creates a new
     * list as to not mess with the original list or the second list.
     * 
     * param - SortedLinkedList secondList
     * 
     * @return - void
     */
    public void mergeList(SortedLinkedList secondList) {

        NodeType firstSearch = head;
        NodeType secondSearch = secondList.head;

        SortedLinkedList merged = new SortedLinkedList();

        // loop through both list and add each variable to a new merged list
        while (firstSearch != null && secondSearch != null) {
            if (firstSearch.info.getValue() < secondSearch.info.getValue()) {
                ItemType item = new ItemType();
                item.initialize(firstSearch.info.getValue());
                merged.insertItem(item);
                firstSearch = firstSearch.next;
            } else if (firstSearch.info.getValue() > secondSearch.info.getValue()) {
                ItemType item = new ItemType();
                item.initialize(secondSearch.info.getValue());
                merged.insertItem(item);
                secondSearch = secondSearch.next;
            } else {
                ItemType item = new ItemType();
                item.initialize(firstSearch.info.getValue());
                merged.insertItem(item);
                firstSearch = firstSearch.next;
                secondSearch = secondSearch.next;
            }
        }

        // add whatever values were left out from remaining lists
        if (firstSearch != null) {
            while (firstSearch != null) {
                ItemType item = new ItemType();
                item.initialize(firstSearch.info.getValue());
                merged.insertItem(item);
                firstSearch = firstSearch.next;
            }
        } else if (secondSearch != null) {
            while (secondSearch != null) {
                ItemType item = new ItemType();
                item.initialize(secondSearch.info.getValue());
                merged.insertItem(item);
                secondSearch = secondSearch.next;
            }
        }

        System.out.print("The list 1: ");
        System.out.println(this);
        System.out.print("The list 2: ");
        System.out.println(secondList);
        System.out.print("Merged list: ");
        System.out.println(merged);

    }

    /*
     * This method goes through the original list and deletes
     * everyother item from the list.
     * 
     * param - none
     * 
     * @return - void
     */
    public void deleteAlternate() {

        System.out.print("Original list: ");
        System.out.println(this);
        if (head == null) {
            System.out.println("The list is empty");
            System.out.print("Modified list: ");
            System.out.println(this);
            return;
        }
        // have 3 holding nodes
        // currNode to iterate; newList to hold new variables; newStart to maintain the
        // head of the new list
        NodeType currNode = head;
        NodeType newList = currNode;
        NodeType newStart = newList;

        while (currNode != null && currNode.next != null) {
            currNode = currNode.next.next;
            newList.next = currNode;
            newList = newList.next;
        }
        // make head equal to the start of the new list
        head = newStart;
        System.out.print("Modified list: ");
        System.out.println(this);
    }

    /*
     * This method takes the secondList and then compares it's values
     * to the original list. While maintaining their sorted order, only
     * matching values from both lists are added to a new intersect list.
     * Once finished the intersect list is printed. A new list is created
     * as to not mess with the original list or the second list.
     * 
     * param - SortedLinkedList secondList
     * 
     * @return - void
     */
    public void intersection(SortedLinkedList secondList) {

        NodeType firstSearch = head;
        NodeType secondSearch = secondList.head;

        SortedLinkedList intersect = new SortedLinkedList();

        // loop through both lists and only add values that match to intersect list
        while (firstSearch != null && secondSearch != null) {
            if (firstSearch.info.getValue() < secondSearch.info.getValue()) {
                firstSearch = firstSearch.next;
            } else if (firstSearch.info.getValue() > secondSearch.info.getValue()) {
                secondSearch = secondSearch.next;
            } else {
                ItemType item = new ItemType();
                item.initialize(firstSearch.info.getValue());
                intersect.insertItem(item);
                firstSearch = firstSearch.next;
                secondSearch = secondSearch.next;
            }
        }

        System.out.print("List 1: ");
        System.out.println(this);
        System.out.print("List 2: ");
        System.out.println(secondList);
        System.out.print("Intersection of lists: ");
        System.out.println(intersect);
    }

    @Override
    /*
     * This is a helper method that allows me to quickly call <someList>.toString()
     * and print the list without having to reloop through the items everytime
     * I want to print a list.
     * 
     * param - none
     * 
     * @return - none
     */
    public String toString() {
        // use a StringBuilder object because it is mutable and the size change without
        // taking up more memory

        StringBuilder string = new StringBuilder();

        NodeType currNode = head;

        while (currNode != null) {
            string.append(currNode.info.getValue()).append(" ");
            currNode = currNode.next;
        }

        return string.toString();
    }
}