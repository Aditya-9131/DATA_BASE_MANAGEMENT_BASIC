import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SuperKeyFinder {
    public static void main(String[] args) {
        String filename = "Keys.txt"; // Change to the actual filename if different
        Relation rel = readKeysFromFile(filename);

        System.out.println("All Super Keys:");
        findSuperKeys(rel);
    }

    static class Relation {
        List<String> attributes;

        Relation() {
            this.attributes = new ArrayList<>();
        }
    }

    private static Relation readKeysFromFile(String filename) {
        Relation rel = new Relation();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line != null) {
                String[] tokens = line.split(",");
                for (String token : tokens) {
                    rel.attributes.add(token.trim());
                }
            }

            // Read the remaining lines (if any)
            while ((line = br.readLine()) != null) {
                // Process each line if needed
                // This is where you might want to build the relation from the file content
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rel;
    }

    private static void findSuperKeys(Relation rel) {
        // Print all individual attributes as super keys
        for (String attribute : rel.attributes) {
            System.out.println("{" + attribute + "}");
        }

        // Print combinations of attributes as super keys
        for (int i = 0; i < rel.attributes.size() - 1; i++) {
            for (int j = i + 1; j < rel.attributes.size(); j++) {
                System.out.println("{" + rel.attributes.get(i) + ", " + rel.attributes.get(j) + "}");
            }
        }
    }
}

