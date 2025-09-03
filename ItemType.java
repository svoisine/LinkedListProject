public class ItemType {
    private int value;

    public int compareTo(ItemType item) {
        if (this.value < item.value)
            return -1;
        else if (this.value == item.value)
            return 0;
        else
            return 1;
    }

    public int getValue() {
        return this.value;
    }

    public void initialize(int num) {
        this.value = num;
    }
}
