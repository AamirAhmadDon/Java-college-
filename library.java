/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practice;

/**
 *
 * @author Aamir
 */
public class library {
    
    static int numberOfBooks = 0;
    // shared by all book objects
    
    String title;
    String author;
    int yearOfPublication;
    
    // Constructor
    public Library(String title, String author, int yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        
        // increase count whenever a new book object is created
        numberOfBooks++;
    }
    
    // method to display class info
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year of Publication: " + yearOfPublication);
    }
    
    // static method to display total number of books
    public static void showTotalBooks() {
        System.out.println("Total number of books created: " + numberOfBooks);
    }
    
    // main method
    public static void main(String[] args) {
        Library book1 = new Library("1984", "George Orwell", 1949);
        Library book2 = new Library("To Kill a Mockingbird", "Harper Lee", 1960);
        
        book1.displayInfo();
        System.out.println();
        book2.displayInfo();
        System.out.println();
        
        Library.showTotalBooks();
    }
}
