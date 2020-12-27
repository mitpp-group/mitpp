
public class ArraySwapper extends Thread{
    private int[] list;

    ArraySwapper(int[] list) {
        this.list = list.clone();
    }

    //Swap even elements of list
    @Override
    public void run() {
        for(int i = 0; i < this.list.length; i++){
            if(i % 2 != 0) continue;
            int temp = list[i];
            list[i] = list[this.list.length - 1 - i];
            list[this.list.length - 1 - i] = temp;
        }
    }

    int[] getList() {
        return list;
    }
}
