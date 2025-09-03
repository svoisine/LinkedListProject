/*
 *  Class ItemType
 * 
 *  This class creates the ItemType object as well
 *  as creates methods that allow the user to initalize
 *  a value for the ItemType object.
 * 
 *  Name: Seth Voisine
 *  UGA ID: 81196637
 *  Date: 09/02/2025
 */
public class ItemType {
    private int value;

    /*
     * This method compares the current ItemType
     * to the other ItemType and returns a number
     * based on if one is greater than (-1), equal
     * to (0) or less than(1).
     * 
     * param - ItemType item
     * 
     * @return - int -1,0,1
     */
    public int compareTo(ItemType item) {
        if (this.value < item.value) {
            return -1;
        } else if (this.value == item.value) {
            return 0;
        } else {
            return 1;
        }
    }

    /*
     * The method gets the direct integer value from the item.
     * 
     * param - none
     * 
     * @return - int value
     */
    public int getValue() {
        return this.value;
    }

    /*
     * This method essentially constructs the ItemType object's
     * value to be the int num.
     * 
     * param - int num
     * 
     * @return - void
     */
    public void initialize(int num) {
        this.value = num;
    }
}
