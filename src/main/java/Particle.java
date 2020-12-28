import java.util.Random;

//particle in crystal
public class Particle extends Thread {
    private static final double RIGHT_PROBABILITY = 0.7;
    private int position = 0;
    private double timeToExecute;
    private double timeToSleep;
    private int numberOfMoves;
    private ParticlesPositions globalPositions;

    Particle(ParticlesPositions globalPositions, double timeToExecute, double timeToSleep, int numberOfMoves) {
        this.timeToExecute = timeToExecute;
        this.timeToSleep = timeToSleep;
        this.numberOfMoves = numberOfMoves;
        this.globalPositions = globalPositions;
    }

    //run particle in separate thread
    @Override
    public void run() {
        if(numberOfMoves == 0){
           long startTime =  System.nanoTime();
           while(startTime + (timeToExecute * 1000_000_000L) > System.nanoTime()){
               try {
                   int currentPosition = this.position;
                   int newPosition = getNewPosition();
                   this.position = newPosition;
                   globalPositions.changePosition(currentPosition, newPosition);
                   Thread.sleep((long) (timeToSleep * 1000));
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        } else {
            for (int i = 0; i < numberOfMoves; i++){
                int currentPosition = this.position;
                int newPosition = getNewPosition();
                this.position = newPosition;
                globalPositions.changePosition(currentPosition, newPosition);
                try {
                    Thread.sleep((long) (timeToSleep * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //calculates new position
    private int getNewPosition(){
        if(new Random().nextDouble() <= RIGHT_PROBABILITY){
            System.out.println("right");
            return globalPositions.getSize() - 1 == this.position ? this.position : this.position + 1;
        } else {
            System.out.println("left");
            return this.position == 0 ? this.position : this.position - 1;
        }
    }
}