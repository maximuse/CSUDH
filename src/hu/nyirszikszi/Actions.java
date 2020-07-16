package hu.nyirszikszi;

import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Actions {
    static ArrayList<Csudh> fileToList(String fileName) {
        ArrayList<Csudh> list = new ArrayList<>();

        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "r");
            String row = raf.readLine();
            row = raf.readLine();
            String[] slice;

            while (row != null) {
                slice = new String(row.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8).split(";");

                list.add(new Csudh(slice[0], slice[1]));

                row = raf.readLine();
            }

            raf.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    static int task3(ArrayList<Csudh> list) {
        return list.size();
    }

    //static  ArrayList<ArrayList<String>> partsOfDomain(ArrayList<Csudh> list) {
    static  ArrayList<ArrayList<String>> partsOfDomain(Csudh list) {
        /*ArrayList<String[]> result = new ArrayList<>();
        String[] slice;

        for (Csudh l : list) {
            slice = l.getDomainName().split("\\.");
            //System.out.println(Arrays.toString(slice));
            Collections.reverse(Arrays.asList(slice));
            //System.out.println(Arrays.toString(slice));

            result.add(slice);
        }*/

        /*ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> data;
        String[] slice;

        for (Csudh l : list) {
            slice = l.getDomainName().split("\\.");
            Collections.reverse(Arrays.asList(slice));

            data = new ArrayList<>(Arrays.asList(slice));

            result.add(data);
        }

        return result;*/

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> data;
        String[] slice;

        slice = list.getDomainName().split("\\.");
        Collections.reverse(Arrays.asList(slice));

        data = new ArrayList<>(Arrays.asList(slice));

        result.add(data);

        return result;
    }

    static String Domain(ArrayList<Csudh> list, int index, int level) {
        Csudh data = list.get(index);
        int i = level - 1;

        try {
            return Actions.partsOfDomain(data).get(0).get(i);
        }
        catch (Exception e) {
            return "nincs";
        }
    }

    static void task5(ArrayList<Csudh> list, int index) {
        System.out.println("5. feladat: Az " + (index + 1) + ". domain felépítése:");

        for (int i = 1; i <= 5; i++) {
            System.out.println("\t" + (i + 1) + ". szint: " + Domain(list, index, i));
        }
    }
}