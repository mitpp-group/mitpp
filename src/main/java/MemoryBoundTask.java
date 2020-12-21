import java.util.List;

class MemoryBoundTask extends Thread {
    private List<Integer> array;

    MemoryBoundTask(List<Integer> array){
        this.array= array;
    }

    @Override
    public void run() {
        for(int i = 0; i < array.size(); i++){
            double result = array.get(i);
        }
    }
}
