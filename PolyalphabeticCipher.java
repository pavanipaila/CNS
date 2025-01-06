import java.util.Scanner;
public class PolyalphabeticCipher {
    public static String encrypt(String text, String key) {
        StringBuilder encrypted = new StringBuilder();
        int keyIndex = 0;
        key = key.toLowerCase();
        for (char ch : text.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char keyChar = key.charAt(keyIndex % key.length());
                encrypted.append((char) ((ch - base + (keyChar - 'a')) % 26 + base));
                keyIndex++;
            } else {
                encrypted.append(ch); 
            }
        }
        return encrypted.toString();
    }
    public static String decrypt(String text, String key) {
        StringBuilder decrypted = new StringBuilder();
        int keyIndex = 0;
        key = key.toLowerCase();
        for (char ch : text.toCharArray()) {
            if (Character.isAlphabetic(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char keyChar = key.charAt(keyIndex % key.length());
                decrypted.append((char) ((ch - base - (keyChar - 'a') + 26) % 26 + base));
                keyIndex++;
            } else {
                decrypted.append(ch); 
            }
        }
        return decrypted.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String text = scanner.nextLine();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted text: " + decrypted);

        scanner.close();
    }
}