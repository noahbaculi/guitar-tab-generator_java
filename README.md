# guitar-tab-generator
Created by Noah Baculi and Adri Regalado.

February 2019

Guitar tab generator from note names considering difficulty of different finger positions.

## How to run:
1. Download the project files to a desired directory.
2. Create a notes source '.txt' file titled '[song_title]_notes.txt' in the first level of the downloaded directory.
	ex: 'fur_elise_notes.txt'
3. Populate the notes source file with the pitches (including octaves) for the song, with one line for each beat and an empty line for a measure break.
	ex: '''*fur_elise_notes.txt*
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
		'''
4. Open the 'TabGenerator.java' file and replace the 'sourceFile' variable filename assignment to point to the desired source notes file.
	ex: 'File sourceFile = new File("fur_elise_notes.txt");'
5. The 'outputRowLength' variable can also be modified to change the length of the output lines as desired.
	ex: 'int outputRowLength = 20;'
6. Run the 'TabGenerator.java' file in the java IDE of choice.
7. For the guitar tuning prompt, enter the desired guitar tuning composition (standard, open G, ...).
8. A 'tab_[song_title]_notes.txt' will be saved to the same directory.