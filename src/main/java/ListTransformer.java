import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

class ListTransformer {
    private int[] list;

    //Constructor creates list specified capacity
    ListTransformer(int numberOfValues) {
        list = new int[numberOfValues];
        Random rd = new Random();
        for(int i = 0; i < numberOfValues; i++) list[i] = rd.nextInt(numberOfValues);
    }

    private int[] getList() {
        return list;
    }

    //Creates and starts list transformers
    void execute() throws IOException {
        ArraySorter as = new ArraySorter(getList());
        as.start();
        ArraySwapper asw = new ArraySwapper(getList());
        asw.start();
        ArrayNewGenerator ang = new ArrayNewGenerator(getList());
        ang.start();
        System.out.println("as: " + Arrays.toString(as.getList()));
        System.out.println("asw: " + Arrays.toString(asw.getList()));
        System.out.println("ang: " + Arrays.toString(ang.getList()));
        ExcelEditor excelEditor = new ExcelEditor();
        excelEditor.addRow("initial", getList(), getList());
        excelEditor.addRow("sorted", getList(), as.getList());
        excelEditor.addRow("swapped", getList(), asw.getList());
        excelEditor.addRow("generated", getList(), ang.getList());
        excelEditor.saveToFile();
    }

}
