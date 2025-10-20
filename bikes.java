/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practice;

/**
 *
 * @author Aamir
 */
public class bikes {
    static int numberOfbikes = 0;
    //shared by all the bike objects
    
    String brand;
    String color;
    int speed;
    //Constructors
    public bikes(String brand,String color, int speed){
    this.brand = brand;
    this.color = color;
    this.speed = speed;
    
    //increase the count every time the class bike is called by +1
    numberOfbikes++;
    
            }
    //method to display the class info
    public void displayInfo(){
        System.out.println ("brand: "+brand);
        System.out.println ("color: " + color);
        System.out.println ("speed:" + speed);
        
    }
    
    //main method to use only static variables
    public static void showTotalbikes(){
        System.out.println("total number of bikes created are: " + numberOfbikes);
    }
    
    //main method
    public static void main(String[]args){
        bike bike1 = new bike("Ducati" , "Ducati Red" , "1000");
        bike bike2 = new bike("Honda" , "Repsol Liverly" , "900");
        
    }
}
