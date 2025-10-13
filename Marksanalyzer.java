package com.mycompany.marksanalyzer;

import java.util.Scanner;

public class Marksanalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        // Create arrays to store student names and marks
        String[] names = new String[numStudents];
        int[] marks = new int[numStudents];

        // Input student names and marks
        for (int i = 0; i < numStudents; i++) {
            System.out.print("\nEnter the name of student " + (i + 1) + ": ");
            names[i] = scanner.next();

            System.out.print("Enter the marks of " + names[i] + ": ");
            marks[i] = scanner.nextInt();

            // Validate marks
            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid marks. Marks must be between 0 and 100.");
                i--; // repeat the iteration for invalid input
            }
        }

        // Display original data
        System.out.println("\n--- ORIGINAL DATA ---");
        displayStudents(names, marks);

        // Sort data
        bubbleSort(names, marks);

        // Display sorted data
        System.out.println("\n--- SORTED DATA (By Marks - Descending) ---");
        displayStudents(names, marks);

        // Calculate and display statistics
        calculateStatistics(marks);

        // Grade distribution
        System.out.println("\n--- GRADE DISTRIBUTION ---");
        displayGradeDistribution(marks);

        scanner.close();
    }

    // Method to display names and marks
    public static void displayStudents(String[] names, int[] marks) {
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + " - " + marks[i] + " (" + getGrade(marks[i]) + ")");
        }
    }

    // Bubble sort by marks (descending)
    public static void bubbleSort(String[] names, int[] marks) {
        int n = marks.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (marks[j] < marks[j + 1]) {
                    // swap marks
                    int tempMark = marks[j];
                    marks[j] = marks[j + 1];
                    marks[j + 1] = tempMark;

                    // swap names
                    String tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                }
            }
        }
    }

    // Calculate and print average, highest, lowest, pass/fail count
    public static void calculateStatistics(int[] marks) {
        int total = 0, highest = marks[0], lowest = marks[0];
        int passCount = 0, failCount = 0;

        for (int mark : marks) {
            total += mark;
            if (mark > highest) highest = mark;
            if (mark < lowest) lowest = mark;
            if (mark >= 40) passCount++;
            else failCount++;
        }

        double average = (double) total / marks.length;

        System.out.println("\n--- STATISTICS ---");
        System.out.println("Average Marks: " + average);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);
        System.out.println("Pass Count (>=40): " + passCount);
        System.out.println("Fail Count (<40): " + failCount);
    }

    // Display grade distribution
    public static void displayGradeDistribution(int[] marks) {
        int gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0, gradeF = 0;

        for (int mark : marks) {
            if (mark >= 80) gradeA++;
            else if (mark >= 60) gradeB++;
            else if (mark >= 40) gradeC++;
            else if (mark >= 30) gradeD++;
            else gradeF++;
        }

        System.out.println("Grade A (80-100): " + gradeA);
        System.out.println("Grade B (60-79): " + gradeB);
        System.out.println("Grade C (40-59): " + gradeC);
        System.out.println("Grade D (30-39): " + gradeD);
        System.out.println("Grade F (0-29): " + gradeF);
    }

    // Get grade based on marks
    public static String getGrade(int mark) {
        if (mark >= 80) return "A";
        else if (mark >= 60) return "B";
        else if (mark >= 40) return "C";
        else if (mark >= 30) return "D";
        else return "F";
    }
}
