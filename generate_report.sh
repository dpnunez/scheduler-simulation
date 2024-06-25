#!/bin/bash

# Directory containing the .txt files
directory="./src/main/resources/cases"

mvn package
# Loop through each .txt file in the directory
for file in "$directory"/*.txt; do
    # Run your command for each file
    echo "Processing file: $file"

    # Add your command here
    java -cp target/scheduling-simulation-1.0-SNAPSHOT.jar org.example.Main $file 2
    java -cp target/scheduling-simulation-1.0-SNAPSHOT.jar org.example.Main $file 2 SJF-REVERSE

    java -cp target/scheduling-simulation-1.0-SNAPSHOT.jar org.example.Main $file 4
    java -cp target/scheduling-simulation-1.0-SNAPSHOT.jar org.example.Main $file 4 SJF-REVERSE

    java -cp target/scheduling-simulation-1.0-SNAPSHOT.jar org.example.Main $file 8
    java -cp target/scheduling-simulation-1.0-SNAPSHOT.jar org.example.Main $file 8 SJF-REVERSE
done


# Create a variable to store the file names
files=""

# Loop through each file that follows the pattern "./result_*.txt"
for file in ./results_*.txt; do
    # Concatenate the file names to the variable
    files="$files $file"
done


# Call the Python script with the file names as arguments
python3 ./generate_charts.py $files