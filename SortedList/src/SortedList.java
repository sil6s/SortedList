import java.util.ArrayList;

public class SortedList {
    private ArrayList<String> list;

    public SortedList() {
        this.list = new ArrayList<>();
    }

    public void add(String element) {
        int index = binarySearch(element);
        if (index < 0) {
            index = -(index + 1); // Get the insertion point
        }
        list.add(index, element); // Correct usage of add with index
    }

    int binarySearch(String key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = list.get(mid).compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid; // Key found
            }
        }
        return -(low + 1); // Key not found
    }

    public String getList() {
        return list.toString();
    }

    public void clearList() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public boolean contains(String element) {
        return binarySearch(element) >= 0;
    }
}