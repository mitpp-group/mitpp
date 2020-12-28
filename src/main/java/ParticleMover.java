class ParticleMover {
    private static final int NUMBER_OF_PARTICLES = 10;
    private static final int NUMBER_OF_CELLS = 15;

    //execute program in mode of limited time
    void executeForTimeWithDelay(double time, double delay){
        ParticlesPositions particlesPositions = new ParticlesPositions(NUMBER_OF_CELLS, NUMBER_OF_PARTICLES);
        for(int i = 0; i < NUMBER_OF_PARTICLES; i++){
            new Particle(particlesPositions, time, delay, 0).start();
        }
    }

    //execute program in mode of limited iterations
    void executeForNumberOfIterationsWithDelay(int numberOfIterations, double delay){
        ParticlesPositions particlesPositions = new ParticlesPositions(NUMBER_OF_CELLS, NUMBER_OF_PARTICLES);
        for(int i = 0; i < NUMBER_OF_PARTICLES; i++){
            new Particle(particlesPositions, 0, delay, numberOfIterations).start();
        }
    }

}
