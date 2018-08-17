package Lesson_3;

import java.io.*;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {

////Task1
//        try (FileInputStream in = new FileInputStream("test.txt")) {
//            byte[] arr = new byte[512];
//            int x;
//            while ((x = in.read(arr)) != -1) {
//                System.out.println(new String(arr, 0, x));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//// Task2
//        ArrayList<InputStream> ali = new ArrayList<>();
//        try {
//            ali.add(new FileInputStream("1.txt"));
//            ali.add(new FileInputStream("2.txt"));
//            ali.add(new FileInputStream("3.txt"));
//            ali.add(new FileInputStream("4.txt"));
//            ali.add(new FileInputStream("5.txt"));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        SequenceInputStream in = new SequenceInputStream((Collections.enumeration(ali)));
//        int x;
//        while ((x = in.read()) != -1) {
//            System.out.print((char) x);
//        }
// Task3

    }
}
