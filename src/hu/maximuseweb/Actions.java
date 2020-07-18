package hu.maximuseweb;

import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Actions {
    private static ArrayList<Csudh> list;

    private static ArrayList<Csudh> getList() {
        return list;
    }

    private static void setList(ArrayList<Csudh> list) {
        Actions.list = list;
    }

    static void fileToList(String fileName) {
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

            setList(list);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static int task3() {
        return Actions.getList().size();
    }

    private static ArrayList<ArrayList<String>> partsOfDomain(Csudh list) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> data;
        String[] slice;

        slice = list.getDomainName().split("\\.");
        Collections.reverse(Arrays.asList(slice));

        data = new ArrayList<>(Arrays.asList(slice));

        result.add(data);

        return result;
    }

    private static String Domain(int index, int level) {
        Csudh data = Actions.getList().get(index);
        int i = level - 1;

        try {
            return Actions.partsOfDomain(data).get(0).get(i);
        }
        catch (Exception e) {
            return "nincs";
        }
    }

    static void task5(int index) {
        System.out.println("5. feladat: Az " + (index + 1) + ". domain felépítése:");

        for (int i = 1; i <= 5; i++) {
            System.out.println("\t" + (i + 1) + ". szint: " + Domain(index, i));
        }
    }

    static void task6(String fileName) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "rw");

            raf.writeBytes("<table>\r\n" +
                    "\t<tr>\r\n" +
                    "\t\t<th style='text-align:left'>Ssz</th>\r\n" +
                    "\t\t<th style='text-align:left'>Hoszt domain neve</th>\r\n" +
                    "\t\t<th style='text-align:left'>Hoszt IP címe</th>\r\n" +
                    "\t\t<th style='text-align:left'>1. szint</th>\r\n" +
                    "\t\t<th style='text-align:left'>2. szint</th>\r\n" +
                    "\t\t<th style='text-align:left'>3. szint</th>\r\n" +
                    "\t\t<th style='text-align:left'>4. szint</th>\r\n" +
                    "\t\t<th style='text-align:left'>5. szint</th>\r\n" +
                    "\t</tr>\r\n");

            for (int i = 0; i < Actions.getList().size(); i++) {
                raf.writeBytes("\t<tr>\r\n" +
                        "\t\t<td>" + (i + 1) + ".</td>\r\n" +
                        "\t\t<td>" + Actions.getList().get(i).getDomainName() + "</td>\r\n" +
                        "\t\t<td>" + Actions.getList().get(i).getIpAddress() + "</td>\r\n" +
                        "\t\t<td>" + Domain(i, 1) + "</td>\r\n" +
                        "\t\t<td>" + Domain(i, 2) + "</td>\r\n" +
                        "\t\t<td>" + Domain(i, 3) + "</td>\r\n" +
                        "\t\t<td>" + Domain(i, 4) + "</td>\r\n" +
                        "\t\t<td>" + Domain(i, 5) + "</td>\r\n" +
                        "\t</tr>\r\n");
            }

            raf.writeBytes("</table>");
            raf.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}