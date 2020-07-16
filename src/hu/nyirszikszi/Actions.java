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

    private static  ArrayList<ArrayList<String>> partsOfDomain(Csudh list) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> data;
        String[] slice;

        slice = list.getDomainName().split("\\.");
        Collections.reverse(Arrays.asList(slice));

        data = new ArrayList<>(Arrays.asList(slice));

        result.add(data);

        return result;
    }

    private static String Domain(ArrayList<Csudh> list, int index, int level) {
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

    static void task6(ArrayList<Csudh> list, String fileName) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "rw");

            raf.writeBytes(new String("<table>\r\n" +
                    "<tr>\r\n" +
                    "<th style='text-align:left'>Ssz</th>\r\n" +
                    "<th style='text-align:left'>Hoszt domain neve</th>\r\n" +
                    "<th style='text-align:left'>Hoszt IP címe</th>\r\n" +
                    "<th style='text-align:left'>1. szint</th>\r\n" +
                    "<th style='text-align:left'>2. szint</th>\r\n" +
                    "<th style='text-align:left'>3. szint</th>\r\n" +
                    "<th style='text-align:left'>4. szint</th>\r\n" +
                    "<th style='text-align:left'>5. szint</th>\r\n" +
                    "</tr>\r\n"));

            for (int i = 0; i < list.size(); i++) {
                raf.writeBytes(new String("<tr>\r\n" +
                        "<td>" + (i + 1) + ".</td>\r\n" +
                        "<td>" + list.get(i).getDomainName() + "</td>\r\n" +
                        "<td>" + list.get(i).getIpAddress() + "</td>\r\n" +
                        "<td>" + Domain(list, i, 1) + "</td>\r\n" +
                        "<td>" + Domain(list, i, 2) + "</td>\r\n" +
                        "<td>" + Domain(list, i, 3) + "</td>\r\n" +
                        "<td>" + Domain(list, i, 4) + "</td>\r\n" +
                        "<td>" + Domain(list, i, 5) + "</td>\r\n" +
                        "</tr>\r\n"));
            }

            raf.writeBytes(new String("</table>"));
            raf.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}