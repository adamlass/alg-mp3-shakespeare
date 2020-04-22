package sp2;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException {
        String[] seqs = FileUtility.toStringArray("scw.txt", " ");

        MySuffixTrie S = new MySuffixTrie(seqs);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Search:");
            String search = scanner.next();
            Set<String> results =  S.find(search);
            System.out.println("Search for '" + search + "' - Found " +results.size() + " results:");
            System.out.println(results);
            System.out.println("-------------------------------------");
        }
    }
}
