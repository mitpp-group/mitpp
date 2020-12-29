import java.util.ArrayList;
import java.util.List;

//save global state of particles in crystal
class ParticlesPositions {
    private List<Integer> positions;

    ParticlesPositions(int numberOfCells, int numberOfParticles) {
        positions = new ArrayList<>();
        for(int i = 0; i < numberOfCells; i++){
            positions.add(i == 0 ? numberOfParticles : 0);
        }
    }

    //change position of particle in crystal
    synchronized void changePosition(int prevPosition, int nextPosition){
        positions.set(prevPosition, positions.get(prevPosition) - 1);
        positions.set(nextPosition, positions.get(nextPosition) + 1);
        System.out.println("positions: " + positions);
    }

    int getSize(){
        return positions.size();
    }

    int getNumberOfParticlesInCell(int i){
        return positions.get(i);
    }
}
