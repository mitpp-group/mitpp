public class CPUBoundTask extends Thread {
    private long numberOfIterations;

    CPUBoundTask(long numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    @Override
    public void run() {
        for(int i = 0; i < numberOfIterations; i++){
            double result = Math.sin(i);
        }
    }
}
