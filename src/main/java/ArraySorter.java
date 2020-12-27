import java.util.Arrays;

public class ArraySorter extends Thread {
    private int[] list;

    ArraySorter(int[] list) {
        this.list = list.clone();
    }

    //Sort list
    @Override
    public void run() {
        Arrays.sort(getList());
    }

    int[] getList() {
        return list;
    }
}
