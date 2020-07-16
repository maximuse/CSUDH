package hu.nyirszikszi;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Csudh> list = Actions.fileToList("csudh.txt");

        System.out.println("3. feladat: Domain-ek száma: " + Actions.task3(list));
        Actions.task5(list, 0);
        Actions.task6(list, "table.html");
    }
}