/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.Scanner;

// ------------------- Base Class -------------------
class Pet {
    protected String name;
    protected int age;
    protected String breed;
    protected boolean isAdopted;

    public Pet(String name, int age, String breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.isAdopted = false;
    }

    public void adopt() {
        isAdopted = true;
    }

    public boolean isAvailable() {
        return !isAdopted;
    }

    public String getType() {
        return "Pet";
    }

    @Override
    public String toString() {
        return name + " (" + breed + ", " + age + " yrs)";
    }
}

// ------------------- Subclass: Cat -------------------
class Cat extends Pet {
    private boolean indoor;

    public Cat(String name, int age, String breed, boolean indoor) {
        super(name, age, breed);
        this.indoor = indoor;
    }

    @Override
    public String getType() {
        return "Cat";
    }

    @Override
    public String toString() {
        return super.toString() + (indoor ? " - Indoor 🏠" : " - Outdoor 🌳");
    }
}

// ------------------- Subclass: Dog -------------------
class Dog extends Pet {
    private boolean trained;

    public Dog(String name, int age, String breed, boolean trained) {
        super(name, age, breed);
        this.trained = trained;
    }

    @Override
    public String getType() {
        return "Dog";
    }

    @Override
    public String toString() {
        return super.toString() + (trained ? " - Trained 🐾" : " - Untrained");
    }
}

// ------------------- Adoption Center -------------------
class AdoptionCenter {
    private ArrayList<Pet> pets = new ArrayList<>();

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public void showAvailablePets() {
        System.out.println("\n--- Available Pets ---");
        boolean found = false;
        for (Pet p : pets) {
            if (p.isAvailable()) {
                System.out.println(p.getType() + ": " + p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No pets available right now. 🐾");
        }
    }

    // Bubble Sort: Cats first, then Dogs
    public void bubbleSort() {
        int n = pets.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // If right one is a Cat and left one is a Dog → swap
                if (pets.get(j).getType().equals("Dog") &&
                    pets.get(j + 1).getType().equals("Cat")) {
                    Pet temp = pets.get(j);
                    pets.set(j, pets.get(j + 1));
                    pets.set(j + 1, temp);
                }
            }
        }
    }

    public void adoptPet(String name) {
        for (Pet p : pets) {
            if (p.name.equalsIgnoreCase(name) && p.isAvailable()) {
                p.adopt();
                System.out.println("🎉 " + name + " has been adopted!");
                return;
            }
        }
        System.out.println("❌ Pet not found or already adopted!");
    }
}

// ------------------- Main Program -------------------
public class PetAdoptionCenter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdoptionCenter center = new AdoptionCenter();

        // Add initial pets
        center.addPet(new Cat("Luna", 1, "Siamese", true));
        center.addPet(new Dog("Buddy", 2, "Golden Retriever", true));
        center.addPet(new Dog("Rex", 3, "German Shepherd", false));
        center.addPet(new Cat("Milo", 2, "Persian", false));

        // Sort cats first (your preference 😸)
        center.bubbleSort();

        int choice;
        do {
            System.out.println("\n🐾 WELCOME TO THE PET ADOPTION CENTER 🐾");
            System.out.println("1. View Available Pets");
            System.out.println("2. Adopt a Pet");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    center.showAvailablePets();
                    break;
                case 2:
                    System.out.print("Enter the pet’s name to adopt: ");
                    String name = sc.nextLine();
                    center.adoptPet(name);
                    // Sort and display updated list
                    center.bubbleSort();
                    center.showAvailablePets();
                    break;
                case 3:
                    System.out.println("Goodbye! 🐕🐈");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 3);

        sc.close();
    }
}
