import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptorDecryptor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for action (encryption or decryption)
        System.out.print("Choose an action (encrypt/decrypt): ");
        String action = scanner.nextLine().trim().toLowerCase();

        // Prompt user for file path
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine().trim();

        // Prompt user for encryption/decryption key
        System.out.print("Enter the encryption/decryption key (an integer): ");
        int key = scanner.nextInt();

        // Process the file based on the chosen action
        switch (action) {
            case "encrypt":
                encryptFile(filePath, key);
                System.out.println("File encrypted successfully.");
                break;
            case "decrypt":
                decryptFile(filePath, key);
                System.out.println("File decrypted successfully.");
                break;
            default:
                System.out.println("Invalid action. Please choose encrypt or decrypt.");
        }

        scanner.close();
    }

    // Method to encrypt a file
    private static void encryptFile(String filePath, int key) {
        processFile(filePath, key, true);
    }

    // Method to decrypt a file
    private static void decryptFile(String filePath, int key) {
        processFile(filePath, key, false);
    }

    // Method to process the file (common method for both encryption and decryption)
    private static void processFile(String filePath, int key, boolean encrypt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Determine output file name based on action
            String outputFilePath;
            if (encrypt) {
                outputFilePath = filePath + ".encrypted";
            } else {
                outputFilePath = filePath + ".decrypted";
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                int currentChar;
                while ((currentChar = reader.read()) != -1) {
                    char encryptedChar = (char) (currentChar + key);
                    writer.write(encryptedChar);
                }
            }
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }
}
