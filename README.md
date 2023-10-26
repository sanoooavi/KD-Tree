# Data Structure Project: Nearest Bank Finder with KD-Tree

This project implements a Nearest Bank Finder using a KD-Tree data structure. The system allows users to find the nearest bank to their location and provides the ability to undo previous commands.

## Overview

The Nearest Bank Finder system utilizes a KD-Tree to efficiently store and retrieve bank locations based on their coordinates. It supports the following features:

1. **Adding Bank Locations**: Users can add bank locations to the KD-Tree by providing the bank name and the coordinates (x, y) of the bank's location.

2. **Adding Bank Branches**: For each bank, users can add multiple branches with their respective names and coordinates. These branches are associated with the main bank location.

3. **Finding Nearest Bank**: Users can find the nearest bank to a specified location by providing the coordinates (x, y) of the desired location. The system uses the KD-Tree to efficiently search for the nearest bank.

4. **Undo Command**: Users have the ability to undo their previous commands. This feature allows them to revert the changes made to the KD-Tree, such as adding bank locations or branches.

## Components

The project consists of the following key components:

1. **Main.java**: This class serves as the entry point for the Nearest Bank Finder system. It provides a command-line interface for users to interact with the system and perform various operations.

2. **KdTree.java**: The KdTree class implements the KD-Tree data structure. It manages the insertion and deletion of branches (bank locations and their branches) in the KD-Tree. It also supports finding the nearest bank to a given location and printing the nodes within a specified area.

3. **Branch.java**: The Branch class represents a bank location or a bank branch. It stores the name of the branch, the associated bank, and the coordinates (x, y) of the location. It also provides methods for comparing branches based on their coordinates.

4. **StateRectangle.java**: The StateRectangle class represents a rectangular area in the state. It is used in the KD-Tree to find and print the nodes within a specified area.

5. **Node.java**: The Node class represents a node in the KD-Tree. It contains a reference to a branch and maintains the left and right child nodes.

## Usage

To use the Nearest Bank Finder system, follow these steps:

1. Run the program by executing the Main.java file.

2. Choose from the available options to perform operations such as adding bank locations, adding bank branches, finding the nearest bank, or undoing commands.

3. When adding a bank location, enter the name of the neighborhood, the minimum and maximum x-coordinates, and the minimum and maximum y-coordinates.

4. When adding a bank, enter the name of the bank and its coordinates (x, y).

5. If desired, you can add branches to a bank by specifying the branch name and its coordinates (x, y).

6. To find the nearest bank to a location, enter the coordinates (x, y) of the desired location.

7. To undo a command, select the undo option. This will revert the last operation performed, such as adding a bank location or adding a branch.

8. Continue using the system by choosing different options or exit the program when finished.

## Conclusion

The Nearest Bank Finder system provides a convenient way to find the nearest bank to a given location using the KD-Tree data structure. It allows users to add bank locations and branches, find the nearest bank, and undo previous commands. By leveraging the efficiency of the KD-Tree, the system ensures fast and accurate results.
