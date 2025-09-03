public class SortedLinkedList {
    private NodeType head;
    private NodeType currentPos;

    public SortedLinkedList() {
        this.head = null;
        this.currentPos = null;
    }

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

    public void insertItem(ItemType item) {

        // if list is empty
        if (head == null) {
            head = new NodeType();
            head.info = item;
            return;
        }

        // first value is a duplicate or insert first value
        if (item.getValue() == head.info.getValue()) {
            System.err.println("Sorry. You cannot insert the duplicate item");
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
            System.err.println("Sorry. You cannot insert the duplicate item");
            return;
        }

        // insert
        NodeType holder = new NodeType();
        holder.info = item;
        holder.next = currNode;
        prev.next = holder;

    }

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

        // check duplicate at insert point
        if (currNode == null) {
            System.out.println("Item not found");
            return;
        }

        prev.next = currNode.next;

    }

    public int searchItem(ItemType item) {
        int index = 0;
        NodeType currNode = head;

        // this loop also handles the base case of an empty list
        while (currNode != null) {
            if (item.getValue() == currNode.info.getValue()) {
                return index; // return the index when we find it
            }
            currNode = currNode.next;
            index++;
        }

        System.out.println("Item Not Found");
        return -1; // return -1 since we did not find the item
    }

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
                System.out.println("The end of the list has been reached");
                currentPos = head;
            } else {
                currentPos = currentPos.next;
            }
        }

        return currentPos.info;
    }

    public void resetList() {
        currentPos = null;
    }

    public void margeList(SortedLinkedList secondList) {
        // merge from this.head and secondList.head, maintaining the sorted order, print
        // after merging
        // only have one of a duplicated number added to the list
        // doesn't change original list
    }

    public void deleteAlternate() {
        // delete every other number in the list, maintaining the sorted order
        // changes the original list
    }

    public void intersection(SortedLinkedList secondList) {
        // print a new list of only values that are the same from both this.head and
        // secondList.head
        // doesn't change the original list
    }
}
