#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SIZE 100
#define MAX_ATTR 26 // Assuming attributes are represented by capital letters

int isSuperKey(char key[], char relation[], int num_attributes) {
    int i, j;
    for (i = 0; i < num_attributes; i++) {
        if (strchr(key, relation[i]) == NULL) {
            return 0;
        }
    }
    return 1;
}

int isCandidateKey(char key[], char relation[], int num_attributes) {
    if (isSuperKey(key, relation, num_attributes)) {
        for (int i = 0; i < num_attributes; i++) {
            char reduced_key[MAX_SIZE];
            strcpy(reduced_key, key);
            for (int j = 0; j < strlen(key); j++) {
                if (j != i) {
                    char temp[2] = {key[j], '\0'};
                    strcat(reduced_key, temp);
                }
            }
            if (isSuperKey(reduced_key, relation, num_attributes)) {
                return 0;
            }
        }
        return 1;
    }
    return 0;
}

int main() {
    FILE *fp;
    char relation[MAX_SIZE];
    char line[MAX_SIZE];
    char superKeys[MAX_SIZE][MAX_SIZE];
    char candidateKeys[MAX_SIZE][MAX_SIZE];
    int num_attributes = 0;

    // Open the file
    fp = fopen("Keys.txt", "r");
    if (fp == NULL) {
        perror("Error opening file");
        return 1;
    }

    // Read the relation and functional dependencies
    fgets(relation, MAX_SIZE, fp);

    // Remove newline character
    relation[strlen(relation) - 1] = '\0';

    // Count the number of attributes
    num_attributes = strlen(relation);

    // Initialize superKeys and candidateKeys arrays
    int numSuperKeys = 0;
    int numCandidateKeys = 0;

    // Read each line of the file and check for super keys and candidate keys
    while (fgets(line, MAX_SIZE, fp) != NULL) {
        // Remove newline character
        line[strlen(line) - 1] = '\0';

        if (isSuperKey(line, relation, num_attributes)) {
            strcpy(superKeys[numSuperKeys], line);
            numSuperKeys++;
        }

        if (isCandidateKey(line, relation, num_attributes)) {
            strcpy(candidateKeys[numCandidateKeys], line);
            numCandidateKeys++;
        }
    }

    // Close the file
    fclose(fp);

    // Print super keys
    printf("Super Keys:\n");
    for (int i = 0; i < numSuperKeys; i++) {
        printf("%s\n", superKeys[i]);
    }

    // Print candidate keys
    printf("\nCandidate Keys:\n");
    for (int i = 0; i < numCandidateKeys; i++) {
        printf("%s\n", candidateKeys[i]);
    }

    return 0;
}
