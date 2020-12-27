import java.util.Random;

public class ArrayNewGenerator extends Thread {
    private int length;
    private int[] list;

    ArrayNewGenerator(int[] list) {
        this.length = list.length;
    }

    //Generates new list with the same length as lst in constructor
    @Override
    public void run() {
        Random rd = new Random();
        list = new int[length];
        for(int i = 0; i < length; i++) list[i] = rd.nextInt(length);
    }

    int[] getList() {
        return list;
    }
}
