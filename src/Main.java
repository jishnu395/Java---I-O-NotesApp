import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int choice;
        do {
            System.out.print("\n--- Notes Manager ---\n1. Add a Note\n2. View Notes\n3. Exit\nEnter your choice: ");
            choice = s.nextInt();
            s.nextLine();

            switch (choice) {
                case 1 -> addNote(s);
                case 2 -> viewNotes();
                case 3 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Try again");
            }
        } while (choice != 3);
        s.close();
    }


    private static void addNote(Scanner s) {
        System.out.print("Enter your Note: ");
        String note = s.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(note);
            System.out.println("Note saved Successfully!");
        } catch (IOException e) {
            System.out.println("ERROR writing to file: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        System.out.println("\n--- Your Notes ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;
            while ((line = br.readLine()) != null) {
                System.out.println("-" + line);
                empty = false;
            }
            if (empty) {
                System.out.println("(No Notes found)");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found yet. Add a note First!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}