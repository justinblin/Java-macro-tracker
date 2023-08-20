import java.util.*;
import java.io.*;

public class tracker {
    public static void main(String[] args) throws IOException {
        //write to txt files using FileWriter
        //read txt files using Scanner
        //look through folders using *file*.list() - returns a string array of things inside *file*

        //resetTodaysMacros();

        addFoodType("zongzi", 275, 8, 39, 10);
        addFoodType("ximilu", 260, 2, 14, 44);

        resetMealMacros();
        eatFood("zongzi", 2);
        eatFood("ximilu", 2);
        eatFood("minipizza", 1);
        checkMealMacros(true);

        checkTodaysMacros(true);
    }

    public static void addFoodType (String foodTypeName, int foodTypeCal, int foodTypeProtein, int foodTypeFat, int foodTypeCarbs) throws IOException {
        //Creating the foodTypes file reference and scanner
        File foodTypesFile = new File("foodTypes.txt");
        Scanner foodTypeScanner = new Scanner(foodTypesFile);

        //Storing the old food types
        boolean isADuplicate = false;
        ArrayList<String> oldFoodTypes = new ArrayList<String>();
        while (foodTypeScanner.hasNext()) {
            String nextFoodType = foodTypeScanner.next();

            if (nextFoodType.equals(foodTypeName)) {//if you find a duplicate, mark it and display a message
                isADuplicate = true;
                System.out.println("ERROR: "+foodTypeName+" is already stored");
            }
            else {//if the current one isn't a duplicate of the one you're adding, store it and move on
                oldFoodTypes.add(nextFoodType);
            }
        }
        foodTypeScanner.close();

        if (!isADuplicate) {//prevents the file from being rewritten if the foodType we're trying to add is a duplicate
            //Creating the file writer, which wipes the file
            FileWriter foodTypeWriter = new FileWriter(foodTypesFile);

            //Re-adding the old food types
            for (int currentFoodType = 0; currentFoodType < oldFoodTypes.size(); currentFoodType++) {
                foodTypeWriter.write(oldFoodTypes.get(currentFoodType) + " ");
            }

            //Adding a new type of food - foodName foodCalories foodProtein foodFat foodCarbs
            foodTypeWriter.write(foodTypeName + " " + foodTypeCal + " " + foodTypeProtein + " " + foodTypeFat + " " + foodTypeCarbs + " ");

            //Closing the writer at the end
            foodTypeWriter.close();

            System.out.println(foodTypeName+" has been added");
        }
    }
    public static void removeFoodType (String foodTypeName) throws IOException {
        //Creating the foodTypes file reference and scanner
        File foodTypesFile = new File("foodTypes.txt");
        Scanner foodTypeScanner = new Scanner(foodTypesFile);

        //Storing the old food types
        boolean hasBeenDeleted = false;
        ArrayList<String> oldFoodTypes = new ArrayList<String>();
        while (foodTypeScanner.hasNext()) {
            String nextFoodType = foodTypeScanner.next();

            if (nextFoodType.equals(foodTypeName)) {//if you find the one you want to delete
                //move the scanner forward 4 times to not include this foodType in the storing arraylist
                for (int a = 0; a < 4; a++) {
                    foodTypeScanner.next();
                }
                hasBeenDeleted = true;
            }
            else {//if the current one isn't it, store it and move on
                oldFoodTypes.add(nextFoodType);
            }
        }
        foodTypeScanner.close();

        if (hasBeenDeleted) {//prevents it from rewriting the file if nothing is actually going to be deleted
            //Creating the file writer, which wipes the file
            FileWriter foodTypeWriter = new FileWriter(foodTypesFile);

            //Re-adding the old food types
            for (int currentFoodType = 0; currentFoodType < oldFoodTypes.size(); currentFoodType++) {
                foodTypeWriter.write(oldFoodTypes.get(currentFoodType) + " ");
            }

            //Closing the writer at the end
            foodTypeWriter.close();

            System.out.println(foodTypeName+" has been deleted");
        }
        else {//display error message if nothing has been deleted
            System.out.println("ERROR: "+foodTypeName+" was not found");
        }
    }

    public static void resetTodaysMacros () throws IOException {
        //Creating the todaysMacros file reference and writer
        File todaysMacrosFile = new File("todaysMacros.txt");
        FileWriter todaysMacrosWriter = new FileWriter(todaysMacrosFile);

        todaysMacrosWriter.write("0 0 0 0");
        todaysMacrosWriter.close();

        System.out.println("Today's macros have been reset");
    }
    public static int[] checkTodaysMacros (boolean display) throws IOException {
        //Creating the todaysMacros file reference and scanner
        File todaysMacrosFile = new File("todaysMacros.txt");
        Scanner todaysMacrosScanner = new Scanner(todaysMacrosFile);

        //find today's calories macros
        int[] todaysMacrosArray = new int[4];
        for (int currentMacro = 0; currentMacro < 4; currentMacro++) {
            todaysMacrosArray[currentMacro] = todaysMacrosScanner.nextInt();
        }

        if (display) {
            //display today's calories and macros
            System.out.print("\n-----Today's Macros-----\nCalories: " + todaysMacrosArray[0] + " ");
            System.out.print("Protein: " + todaysMacrosArray[1] + "g ");
            System.out.print("Fat: " + todaysMacrosArray[2] + "g ");
            System.out.print("Carbs: " + todaysMacrosArray[3] + "g\n\n");
        }

        todaysMacrosScanner.close();
        return todaysMacrosArray;
    }

    public static void resetMealMacros () throws IOException {
        //Creating the mealMacros file reference and writer
        File mealMacrosFile = new File("mealMacros.txt");
        FileWriter mealMacrosWriter = new FileWriter(mealMacrosFile);

        mealMacrosWriter.write("0 0 0 0");
        mealMacrosWriter.close();

        System.out.println("Meal macros have been reset");
    }
    public static int[] checkMealMacros (boolean display) throws IOException {
        //Creating the mealMacros file reference and scanner
        File mealMacrosFile = new File("mealMacros.txt");
        Scanner mealMacrosScanner = new Scanner(mealMacrosFile);

        //find meal's calories macros
        int[] mealMacrosArray = new int[4];
        for (int currentMacro = 0; currentMacro < 4; currentMacro++) {
            mealMacrosArray[currentMacro] = mealMacrosScanner.nextInt();
        }

        if (display) {
            //display meal's calories and macros
            System.out.print("\n-----Meal Macros-----\nCalories: " + mealMacrosArray[0] + " ");
            System.out.print("Protein: " + mealMacrosArray[1] + "g ");
            System.out.print("Fat: " + mealMacrosArray[2] + "g ");
            System.out.print("Carbs: " + mealMacrosArray[3] + "g\n\n");
        }

        mealMacrosScanner.close();
        return mealMacrosArray;
    }

    public static void eatFood (String foodTypeName, double amountOfFood) throws IOException {
        //Creating the foodTypes file reference and scanner
        File foodTypesFile = new File("foodTypes.txt");
        Scanner foodTypeScanner = new Scanner(foodTypesFile);

        //Finding your new calories and macros after eating
        boolean foundTheFood = false;
        int[] todaysMacrosArray = checkTodaysMacros(false);
        int[] mealMacrosArray = checkMealMacros(false);
        while (foodTypeScanner.hasNext()) {
            if (foodTypeScanner.next().equals(foodTypeName)) {//if you find the food type you're looking for
                //add the calories and macros of the food you're eating to today's total and meal's total
                for (int currentMacro = 0; currentMacro < 4; currentMacro++) {
                    int nextMacro = foodTypeScanner.nextInt();

                    todaysMacrosArray[currentMacro] += amountOfFood * nextMacro;
                    mealMacrosArray[currentMacro] += amountOfFood * nextMacro;
                }
                foundTheFood = true;
            }
        }
        foodTypeScanner.close();

        if (foundTheFood) {//prevents it from rewriting the macro file if it couldn't find the food
            //Write down today's new calories and macros
            File todaysMacrosFile = new File("todaysMacros.txt");
            FileWriter todaysMacrosWriter = new FileWriter(todaysMacrosFile);
            for (int currentMacro = 0; currentMacro < 4; currentMacro++) {
                todaysMacrosWriter.write(todaysMacrosArray[currentMacro] + " ");
            }
            todaysMacrosWriter.close();

            //Writ down this meal's new calories and macros
            File mealMacrosFile = new File("mealMacros.txt");
            FileWriter mealMacrosWriter = new FileWriter(mealMacrosFile);
            for (int currentMacro = 0; currentMacro < 4; currentMacro++) {
                mealMacrosWriter.write(mealMacrosArray[currentMacro] + " ");
            }
            mealMacrosWriter.close();

            System.out.println("Ate "+amountOfFood+"x "+foodTypeName);
        }
        else {//displays an error if it couldn't find the food
            System.out.println("ERROR: "+foodTypeName+" couldn't be found");
        }
    }
}