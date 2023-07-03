# guitar-tab-generator_java
Created by Noah Baculi and Adri Regalado.

February 2019

Last Updated Jun 2020

Guitar tab generator from note names considering difficulty of different finger positions.

New versions:
- [Typescript](https://github.com/noahbaculi/guitar-tab-generator_typescript) (2022)
- [Rust)](https://github.com/noahbaculi/guitar-tab-generator) (2023 - 

## How to run (GUI):
1. Download the `Guitar_GUI_Executable.jar` file.
2. Run the `Guitar_GUI_Executable.jar` file.
3. Create or load notes `.txt` file.

	ex: *fur_elise_notes.txt*

	```
	E4
	Eb4
	E4
	Eb4
	E4
	B3
	D4
	C4

	A2A3
	E3
	A3
	C3
	E3
	A3
	```

4. Select the desired guitar tuning.

	ex: *Standard*

5. Click 'Generate' and tune the TAB and row length as desired.
6. Save or copy TAB.



## How to run (Developer):
1. Download the project files to a desired directory.
2. Create a notes source `.txt` file titled `[song_title]_notes.txt` in the first level of the downloaded directory.
	
	ex: `fur_elise_notes.txt`

3. Populate the notes source file with the pitches (including octaves) for the song, with one line for each beat and an empty line for a measure break.
	
	ex: *fur_elise_notes.txt*

	```
	E4
	Eb4
	E4
	Eb4
	E4
	B3
	D4
	C4

	A2A3
	E3
	A3
	C3
	E3
	A3
	```
	
4. Open the `TabGenerator.java` file and replace the `sourceFile` variable filename assignment to point to the desired source notes file.
	
	ex: `File sourceFile = new File("fur_elise_notes.txt");`
	
5. The `outputRowLength` variable can also be modified to change the length of the output lines as desired.
	
	ex: `int outputRowLength = 20;`
	
6. Run the `TabGenerator.java` file in the java IDE of choice.
7. For the guitar tuning prompt, enter the desired guitar tuning composition (standard, open G, ...).
8. A `tab_[song_title]_notes.txt` will be saved to the same directory.
