import java.util.Scanner;

public class CaesarCipher {

    // Encrypts the input text using the shift key
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        shift = shift % 26; // Normalize shift to stay within the range of the alphabet
        
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                char encryptedChar = (char) ((character - base + shift + 26) % 26 + base);
                result.append(encryptedChar);
            } else {
                result.append(character); // Non-alphabetic characters remain unchanged
            }
        }
        return result.toString();
    }

    // Decrypts the input text using the shift key
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26)); // Decrypt by reversing the shift
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Caesar Cipher Implementation");
        System.out.print("Enter the text to encrypt: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the shift key (integer): ");
        int shift = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Encrypt the plaintext
        String encryptedText = encrypt(plaintext, shift);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the text
        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}