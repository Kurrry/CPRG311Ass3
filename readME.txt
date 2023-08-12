Authors: Avery Johnson-Dhillon, Joshua Lokhorst, John Holloway, Eric Gagne

How to install and use:
Using command line type "java -jar WordTracker.jar <some file location> <-po | -pf | -pl> [-f some output file location]" with everything between the <> being placeholders and everything between [] being optional.
please do not include <> or [] in arguments.
WordTracker.jar located in src directory.

Completeness: 100%

Deficiencies and missing funcitonality:
The word tracker will only track the line number that the word appears on in the first file it is found in. 
The BST has no functionality to delete a single node, the user can only clear the entire tree. 
BST deletion relies on JVM garbage colleciton, which can cause significant slow down depending on the size of the tree.
