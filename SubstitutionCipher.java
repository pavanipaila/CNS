import java.util.Scanner;

public class SubstitutionCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Example substitution key
        String key = "QWERTYUIOPLKJHGFDSAZXCVBNM";

        System.out.println("Enter text to encrypt:");
        String plaintext = scanner.nextLine().toUpperCase();

        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    // Method to encrypt using substitution cipher
    public static String encrypt(String plaintext, String key) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = ALPHABET.indexOf(c);
                encrypted.append(key.charAt(index));
            } else {
                encrypted.append(c); // Non-alphabetic characters remain unchanged
            }
        }
        return encrypted.toString();
    }

    // Method to decrypt using substitution cipher
    public static String decrypt(String ciphertext, String key) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = key.indexOf(c);
                decrypted.append(ALPHABET.charAt(index));
            } else {
                decrypted.append(c); // Non-alphabetic characters remain unchanged
            }
        }
        return decrypted.toString();
    }
}