import java.util.Scanner;

public class AutokeyCipher {
    // Method to encrypt plaintext using AutoKey Cipher
    public static String encrypt(String plaintext, String key) {
        StringBuilder extendedKey = new StringBuilder(key);
        extendedKey.append(plaintext); // Append plaintext to the key
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            char k = extendedKey.charAt(i);
            char c = (char) (((p - 'A') + (k - 'A')) % 26 + 'A');
            ciphertext.append(c);
        }
        return ciphertext.toString();
    }

    // Method to decrypt ciphertext using AutoKey Cipher
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        StringBuilder extendedKey = new StringBuilder(key);
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            char k = extendedKey.charAt(i);
            char p = (char) (((c - 'A') - (k - 'A') + 26) % 26 + 'A');
            plaintext.append(p);
            extendedKey.append(p); // Extend key with decrypted plaintext
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Input plaintext and key
            System.out.print("Enter plaintext (lowercase letters only): ");
            String plaintext = sc.nextLine().toLowerCase();
            if (!plaintext.matches("[A-Z]+")) {
                throw new IllegalArgumentException("Plaintext must contain only lowercase letters.");
            }

            System.out.print("Enter key (lowercase letters only): ");
            String key = sc.nextLine().toLowerCase();
            if (!key.matches("[A-Z]+")) {
                throw new IllegalArgumentException("Key must contain only lowercase letters.");
            }

            // Encrypt the plaintext
            String ciphertext = encrypt(plaintext, key);
            System.out.println("Ciphertext: " + ciphertext);

            // Decrypt the ciphertext
            String decryptedText = decrypt(ciphertext, key);
            System.out.println("Decrypted text: " + decryptedText);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
