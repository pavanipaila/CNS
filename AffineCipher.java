import java.util.Scanner;

public class AffineCipher {

    // Encrypts a plaintext string using the equation c = (3x + 12) % 26
    public static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (char character : plaintext.toUpperCase().toCharArray()) {
            if (Character.isLetter(character)) {
                int x = character - 'A'; // Convert character to numeric value (A=0, B=1, ..., Z=25)
                int c = (3 * x + 12) % 26; // Apply the Affine Cipher equation
                ciphertext.append((char) (c + 'A')); // Convert back to character
            } else {
                ciphertext.append(character); // Non-alphabetic characters remain unchanged
            }
        }
        return ciphertext.toString();
    }

    // Decrypts a ciphertext string using the equation x = 9(c - 12) % 26
    public static String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (char character : ciphertext.toUpperCase().toCharArray()) {
            if (Character.isLetter(character)) {
                int c = character - 'A'; // Convert character to numeric value
                int x = (9 * (c - 12 + 26)) % 26; // Apply the decryption equation (add 26 to handle negatives)
                plaintext.append((char) (x + 'A')); // Convert back to character
            } else {
                plaintext.append(character); // Non-alphabetic characters remain unchanged
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Affine Cipher Implementation");
        System.out.print("Enter the text to encrypt: ");
        String plaintext = scanner.nextLine();

        // Encrypt the plaintext
        String encryptedText = encrypt(plaintext);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the ciphertext
        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}