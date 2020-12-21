import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class TasksExecutor {

    //Executes all tasks with different number of threads
    void execute(int maxNumberOfThreads, long totalCallNumber) throws InterruptedException, IOException {
        ExcelEditor excelEditor = new ExcelEditor(maxNumberOfThreads);
        List<Integer> arrayOfNumbers = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < totalCallNumber; i++){
            arrayOfNumbers.add(rand.nextInt());
        }
        List<Double> results = new ArrayList<>();
        for(int i = 0; i < maxNumberOfThreads; i++){
            List<Thread> threads = new ArrayList<>();
            long startTime = System.nanoTime();
            for(int j = 0; j < i + 1; j++){
                Thread t = new CPUBoundTask(totalCallNumber / (j + 1));
                t.start();
                threads.add(t);
            }
            joinAll(threads);
            long endTime = System.nanoTime();
            //Calculating time of task execution
            System.out.println("cpu time: " + (endTime - startTime));
            results.add((double) ((endTime - startTime) / 1000_000));
            System.out.println("");
        }
        System.out.println(results);
        //Adding results to excel file
        excelEditor.addRow("CPUBoundTask", results);

        results = new ArrayList<>();
        for(int i = 0; i < maxNumberOfThreads; i++){
            List<Thread> threads = new ArrayList<>();
            long startTime = System.nanoTime();
            for(int j = 0; j < i + 1; j++){
                Thread t = new MemoryBoundTask(arrayOfNumbers.subList(  arrayOfNumbers.size() / (i + 1) * j,
                        arrayOfNumbers.size() / (i + 1) * (j + 1)));
                t.start();
                threads.add(t);
            }
            joinAll(threads);
            long endTime = System.nanoTime();
            //Calculating time of task execution
            System.out.println("memory time: " + (endTime - startTime));
            results.add((double) ((endTime - startTime) / 1000_000));
            System.out.println("");
        }
        System.out.println(results);
        //Adding results to excel file
        excelEditor.addRow("MemoryBoundTask", results);

        results = new ArrayList<>();
        for(int i = 0; i < maxNumberOfThreads; i++){
            List<Thread> threads = new ArrayList<>();
            long startTime = System.nanoTime();
            for(int j = 0; j < i + 1; j++){
                Thread t = new IOBoundTask(totalCallNumber / (i + 1));
                t.start();
                threads.add(t);
            }
            joinAll(threads);
            long endTime = System.nanoTime();
            //Calculating time of task execution
            System.out.println("io time: " + (endTime - startTime));
            results.add((double) ((endTime - startTime) / 1000_000));
            System.out.println("");
        }
        System.out.println(results);
        //Adding results to excel file
        excelEditor.addRow("IOBoundTask", results);
        //Create chart with added data and save file
        excelEditor.drawCharts();
        excelEditor.saveToFile();
    }

    private void joinAll(List<Thread> threads) throws InterruptedException {
        for(Thread thread : threads) thread.join();
    }
}
