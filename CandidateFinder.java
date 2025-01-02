import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CandidateFinder {
    public static void main(String[] args) {
        String filename = "Keys.txt"; // Change to the actual filename if different
        Relation rel = readKeysFromFile(filename);

        System.out.println("All Candidate Keys:");
        findCandidateKeys(rel);
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

    private static void findCandidateKeys(Relation rel) {
        // Get all super keys
        List<Set<String>> superKeys = generateSuperKeys(rel.attributes);

        // Print only the minimal super keys as candidate keys
        for (Set<String> superKey : superKeys) {
            if (isMinimalSuperKey(superKey, superKeys)) {
                System.out.println(superKey);
            }
        }
    }

    private static List<Set<String>> generateSuperKeys(List<String> attributes) {
        List<Set<String>> superKeys = new ArrayList<>();

        // Add individual attributes as super keys
        for (String attribute : attributes) {
            Set<String> superKey = new HashSet<>();
            superKey.add(attribute);
            superKeys.add(superKey);
        }

        // Add combinations of attributes as super keys
        for (int i = 0; i < attributes.size() - 1; i++) {
            for (int j = i + 1; j < attributes.size(); j++) {
                Set<String> superKey = new HashSet<>();
                superKey.add(attributes.get(i));
                superKey.add(attributes.get(j));
                superKeys.add(superKey);
            }
        }

        return superKeys;
    }

    private static boolean isMinimalSuperKey(Set<String> candidateKey, List<Set<String>> superKeys) {
        for (Set<String> superKey : superKeys) {
            if (!superKey.equals(candidateKey) && superKey.containsAll(candidateKey)) {
                return false;
            }
        }
        return true;
    }
}

