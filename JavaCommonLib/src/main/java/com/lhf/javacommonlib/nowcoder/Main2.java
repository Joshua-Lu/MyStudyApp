package com.lhf.javacommonlib.nowcoder;

import java.util.Scanner;

/**
 * @author Joshua
 * @date 2022/7/27 7:55
 */
public class Main2 {
 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  String s = sc.nextLine();
  System.out.println(f(s));

 }

 public static String f(String s) {
  String[] ss = s.split("\\.");
  if (ss.length != 4) {
   return "NO";
  }
  for (String a : ss) {
   int i = -1;
   try {
    i = Integer.valueOf(a);
   } catch (Exception e) {
    return "NO";
   }

   if (i < 0 || i > 255) {
    return "NO";
   }
  }
  return "YES";
 }

// public static String f(String s) {
//  String s1 = s.replaceAll("\\D", ",");
//  String[] ss = s.split(",");
//  int max = 0;
//  for (String a : ss) {
//   if(a.length() > max) {
//    max = a.length();
//   }
//  }
//  for (String a : ss) {
//   if(a.length() == max) {
//    System.out.print(a);
//   }
//  }
//  System.out.print("," + max);
// }
}
