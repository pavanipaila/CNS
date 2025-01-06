import java.util.Scanner;

public class PlayfairCipher {
    static char[][] keyMatrix = new char[5][5];
    static boolean[] visited = new boolean[26];

    // Generate the key matrix dynamically
    static void generateKeyMatrix(String key) {
        int k = 0;
        key = key.toLowerCase(); // Normalize the key to lowercase
        for (int i = 0; i < 26; i++) visited[i] = false;

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (ch == 'j') ch = 'i';
            if (!visited[ch - 'a']) {
                keyMatrix[k / 5][k % 5] = ch;
                visited[ch - 'a'] = true;
                k++;
            }
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (ch == 'j') continue;
            if (!visited[ch - 'a']) {
                keyMatrix[k / 5][k % 5] = ch;
                visited[ch - 'a'] = true;
                k++;
            }
        }
    }

    // Print the key matrix
    static void printKeyMatrix() {
        System.out.println("Key Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(keyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Format the text
    static String formatText(String text) {
        text = text.replace('j', 'i').toLowerCase();
        StringBuilder formattedText = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                formattedText.append(ch);
            }
        }

        // Add padding if the length is odd
        if (formattedText.length() % 2 != 0) {
            formattedText.append("x");
        }
        return formattedText.toString();
    }

    // Get coordinates of a character in the key matrix
    static void getCoordinates(char letter, int[] coordinates) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == letter) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                    return;
                }
            }
        }
    }

    // Encrypt the text using Playfair cipher
    static String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] coord1 = new int[2];
            int[] coord2 = new int[2];

            getCoordinates(text.charAt(i), coord1);
            getCoordinates(text.charAt(i + 1), coord2);

            if (coord1[0] == coord2[0]) { // Same row
                encryptedText.append(keyMatrix[coord1[0]][(coord1[1] + 1) % 5]);
                encryptedText.append(keyMatrix[coord2[0]][(coord2[1] + 1) % 5]);
            } else if (coord1[1] == coord2[1]) { // Same column
                encryptedText.append(keyMatrix[(coord1[0] + 1) % 5][coord1[1]]);
                encryptedText.append(keyMatrix[(coord2[0] + 1) % 5][coord2[1]]);
            } else { // Rectangle rule
                encryptedText.append(keyMatrix[coord1[0]][coord2[1]]);
                encryptedText.append(keyMatrix[coord2[0]][coord1[1]]);
            }
        }
        return encryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for key and plaintext
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        // Generate and print the key matrix
        generateKeyMatrix(key);
        printKeyMatrix();

        // Format the plaintext and encrypt
        String formattedText = formatText(plaintext);
        String encryptedText = encrypt(formattedText);

        // Display the encrypted text
        System.out.println("Encrypted text: " + encryptedText);

        scanner.close();
    }
}