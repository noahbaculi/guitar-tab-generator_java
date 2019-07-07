import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TabGenerator {

	// create readable list of reference guitar notes for comparison and input
	// validation
	protected static List<String> guitarRangeOrig = new LinkedList<String>(Arrays.asList("E2", "F2", "F#2", "G2", "G#2",
			"A2", "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4",
			"D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5",
			"G5", "G#5", "A5", "A#5", "B5", "C6", "C#6", "D6", "D#6", "E6", ""));

	// create readable list of all notes (to be transposed from )
	protected static List<String> pitchList = new LinkedList<String>(Arrays.asList("A0", "A#0", "B0", "C1", "C#1", "D1",
			"D#1", "E1", "F1", "F#1", "G1", "G#1", "A1", "A#1", "B1", "C2", "C#2", "D2", "D#2", "E2", "F2", "F#2", "G2",
			"G#2", "A2", "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4",
			"C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5",
			"F#5", "G5", "G#5", "A5", "A#5", "B5", "C6", "C#6", "D6", "D#6", "E6", "F6", "F#6", "G6", "G#6", "A6",
			"A#6", "B6", "C7", "C#7", "D7", "D#7", "E7", "F7", "F#7", "G7", "G#7", "A7", "A#7", "B7"));
	// create process-formatted list of reference guitar notes for comparison and
	// input validation
	protected static ArrayList<String> guitarRange = new ArrayList<>();

	// create map with possible chords and tabs in order of Elow A D G B Ehigh in
	// custom method
	protected static LinkedHashMap<String, String> chordMap = CreateChordMap();

	// create running record of notes from which to print out later
	protected static ArrayList<String> ehighRecord = new ArrayList<>();
	protected static ArrayList<String> bRecord = new ArrayList<>();
	protected static ArrayList<String> gRecord = new ArrayList<>();
	protected static ArrayList<String> dRecord = new ArrayList<>();
	protected static ArrayList<String> aRecord = new ArrayList<>();
	protected static ArrayList<String> elowRecord = new ArrayList<>();

	// initialize running variable to record last chosen fret to calculate travel
	protected static float lastFret = 0F;

	// create reference note lists for guitar strings where index of note
	// corresponds to fret
	protected static List<String> ehighString = new LinkedList<String>(
			Arrays.asList("E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5",
					"F#5", "G5", "G#5", "A5", "A#5", "B5", "C6", "C#6", "D6", "D#6", "E6"));
	protected static List<String> bString = new LinkedList<String>(
			Arrays.asList("B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5",
					"C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5"));
	protected static List<String> gString = new LinkedList<String>(
			Arrays.asList("G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4",
					"A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5"));
	protected static List<String> dString = new LinkedList<String>(
			Arrays.asList("D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4",
					"E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5"));
	protected static List<String> aString = new LinkedList<String>(
			Arrays.asList("A2", "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3",
					"B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4"));
	protected static List<String> elowString = new LinkedList<String>(
			Arrays.asList("E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3", "F3",
					"F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4"));

	protected static List<String> tunedStrings = new LinkedList<String>(Arrays.asList("e", "B", "G", "D", "A", "E"));

	protected static int transpose = 0;

	public static void main(String[] args) {
		
		// TODO create 'main' method to improve usability
		
		assignTuningReferences();
		String sourceFileName = "hey_jude_notes.txt";
		List<String> rawNoteGroups = readNoteGroups(sourceFileName);
		ArrayList<String> noteGroups = new ArrayList<>();
		
		System.out.println(rawNoteGroups);

		// clean up input for validation
		for (int ii = 0; ii < rawNoteGroups.size(); ii++) {
			rawNoteGroups.set(ii, rawNoteGroups.get(ii).toLowerCase()); // make all notes lower case
			rawNoteGroups.set(ii, rawNoteGroups.get(ii).replace(" ", "")); // remove all spaces
			rawNoteGroups.set(ii, rawNoteGroups.get(ii).replace(",", "")); // remove all commas
			// change all flats to equivalent sharps for ease of processing
			rawNoteGroups.set(ii, rawNoteGroups.get(ii).replace("gb", "f#"));
			rawNoteGroups.set(ii, rawNoteGroups.get(ii).replace("ab", "g#"));
			rawNoteGroups.set(ii, rawNoteGroups.get(ii).replace("bb", "a#"));
			rawNoteGroups.set(ii, rawNoteGroups.get(ii).replace("db", "c#"));
			rawNoteGroups.set(ii, rawNoteGroups.get(ii).replace("eb", "d#"));
			rawNoteGroups.set(ii, rawNoteGroups.get(ii).replace("gb", "f#"));
			
			if (chordMap.containsKey(rawNoteGroups.get(ii))) {
				noteGroups.add(rawNoteGroups.get(ii));
			}
			else if (rawNoteGroups.get(ii).isBlank()) {
				noteGroups.add(rawNoteGroups.get(ii));
			}
			else {
				noteGroups.add(pitchList.get(pitchList.indexOf(rawNoteGroups.get(ii).toUpperCase())+transpose).toLowerCase());
			}
			
		}
		
		System.out.println(noteGroups);
		
		validateSource(noteGroups);

		// loop through note groups to record frets in string records
		for (int ii = 0; ii < noteGroups.size(); ii++) {
			if (chordMap.containsKey(noteGroups.get(ii))) {
				recordChord(noteGroups, ii);
				// TODO fix chord tabs with different tunings
			}
			// single notes should be 3 characters or less
			else if (noteGroups.get(ii).length() <= 3) {
				recordSingleNote(noteGroups, ii);
			}

			// note groups longer than 3 characters that are not chords are assumed to be
			// multiple notes
			else if (noteGroups.get(ii).length() > 3) {
				recordMultiNote(noteGroups, ii);
			}
		}

		// TODO manage 3 notes at once (even necessary?)

		System.out.println(tunedStrings.get(0) + ": " + ehighRecord + "\n");
		System.out.println(tunedStrings.get(1) + ": " + bRecord + "\n");
		System.out.println(tunedStrings.get(2) + ": " + gRecord + "\n");
		System.out.println(tunedStrings.get(3) + ": " + dRecord + "\n");
		System.out.println(tunedStrings.get(4) + ": " + aRecord + "\n");
		System.out.println(tunedStrings.get(5) + ": " + elowRecord);

		boolean multipleMeasureBreak = false;
		outputTabToFile(sourceFileName);
	}

	private static void assignTuningReferences() {
		String tuning = "";
		Scanner scanObj = new Scanner(System.in); // create a Scanner object
		while (true) {
			System.out.print("Enter the guitar tuning (Standard, Open G, Open D, C6, Dsus4): ");
			tuning = scanObj.nextLine();
			tuning = tuning.replace(" ", ""); // remove all spaces
			tuning = tuning.replace(",", ""); // remove all commas
			tuning = tuning.toLowerCase();
			if (tuning.equals("") || tuning.equals("standard") || tuning.equals("openg") || tuning.equals("opend")
					|| tuning.equals("c6") || tuning.equals("dsus4")) {
				break;
			}
		}
		scanObj.close();

		if (tuning.equals("") || tuning.equals("standard")) {
			return;
		}
		else if (tuning.equals("openg")) {
			guitarRangeOrig = Arrays.asList("D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2", "C3", "C#3",
					"D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4",
					"F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5",
					"G#5", "A5", "A#5", "B5", "C6", "C#6", "D6", "");
			ehighString = Arrays.asList("D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5",
					"D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5", "C6", "C#6", "D6");
			bString = Arrays.asList("B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4",
					"C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5");
			gString = Arrays.asList("G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4",
					"G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5");
			dString = Arrays.asList("D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4",
					"D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5");
			aString = Arrays.asList("G2", "G#2", "A2", "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3",
					"G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4");
			elowString = Arrays.asList("D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2", "C3", "C#3",
					"D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4");
			tunedStrings = Arrays.asList("d", "B", "g", "D", "G", "D");
		}
		else if (tuning.equals("opend")) {
			guitarRangeOrig = Arrays.asList("D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2", "C3", "C#3",
					"D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4",
					"F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5",
					"G#5", "A5", "A#5", "B5", "C6", "C#6", "D6", "");
			ehighString = Arrays.asList("D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5",
					"D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5", "C6", "C#6", "D6");
			bString = Arrays.asList("A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4",
					"A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5");
			gString = Arrays.asList("F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4",
					"G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5");
			dString = Arrays.asList("D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4",
					"D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5");
			aString = Arrays.asList("A2", "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3",
					"A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4");
			elowString = Arrays.asList("D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2", "C3", "C#3",
					"D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4");
			tunedStrings = Arrays.asList(" d", " a", "F#", " d", " A", " D");
		}
		else if (tuning.equals("c6")) {
			guitarRangeOrig = Arrays.asList("C2", "C#2", "D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2",
					"C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4",
					"D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5",
					"F#5", "G5", "G#5", "A5", "A#5", "B5", "C6", "C#6", "D6", "D#6", "E6", "");
			ehighString = Arrays.asList("E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5",
					"E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5", "C6", "C#6", "D6", "D#6", "E6");
			bString = Arrays.asList("C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5",
					"C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5", "C5");
			gString = Arrays.asList("G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4",
					"G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5");
			dString = Arrays.asList("C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4",
					"C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5");
			aString = Arrays.asList("A2", "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3",
					"A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4");
			elowString = Arrays.asList("C2", "C#2", "D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2",
					"C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4");
			tunedStrings = Arrays.asList("E", "c", "G", " C", " A", " C");
		}
		else if (tuning.equals("dsus4")) {
			guitarRangeOrig = Arrays.asList("D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2", "C3", "C#3",
					"D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4",
					"F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5",
					"G#5", "A5", "A#5", "B5", "C6", "C#6", "D6", "");
			ehighString = Arrays.asList("D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5",
					"D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5", "C6", "C#6", "D6");
			bString = Arrays.asList("A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4",
					"A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5");
			gString = Arrays.asList("G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4",
					"G#4", "A4", "A#4", "B4", "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5");
			dString = Arrays.asList("D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4",
					"D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4", "C5", "C#5", "D5");
			aString = Arrays.asList("A2", "A#2", "B2", "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3",
					"A#3", "B3", "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4");
			elowString = Arrays.asList("D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2", "C3", "C#3",
					"D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3", "C4", "C#4", "D4");
			tunedStrings = Arrays.asList("d", "a", "G", "D", "A", "D");
		}
		// TODO implement more tunings
	}

	private static List<String> readNoteGroups(String fileNameSource) {
		for (int ii = 0; ii < guitarRangeOrig.size(); ii++) {
			guitarRange.add(ii, guitarRangeOrig.get(ii).toLowerCase());
		}
		List<String> noteGroups = new ArrayList<>();

		// read source file and split lines
		ArrayList<String> inputLines = new ArrayList<>();
		// change name of source file with list of notes
		try {
			File sourceFile = new File(fileNameSource);
//			noteGroups.add(sourceFile.getName());
			FileReader reader = new FileReader(sourceFile);
			BufferedReader buffIn = new BufferedReader(reader);
			String line;
			while ((line = buffIn.readLine()) != null) {
				inputLines.add(line);
			}
			buffIn.close();
		}
		catch (IOException e) {
			System.err.println(e);
		}

		// identify first line with "chord" or numbers (notes)
		int firstNoteIndex = 0;
		for (int ii = 0; ii < inputLines.size(); ii++) {
			if ((inputLines.get(ii).toLowerCase().matches(".*chord.*"))) {
				firstNoteIndex = ii;
				break;
			}
			if ((inputLines.get(ii).matches(".*\\d.*"))) {
				firstNoteIndex = ii;
				break;
			}
			else if (ii == (inputLines.size() - 1)) {
				System.err.println(
						"No notes were found in the source file. Please double check that octave numbers are specified (ex: A#4)");
			}
		}

		// group notes without the numberless first lines
		noteGroups.addAll((inputLines.subList(firstNoteIndex, inputLines.size())));

		System.out.println("Notes found in file: " + noteGroups);
		System.out.println();

		return noteGroups;
	}

	private static void validateSource(List<String> noteGroups) {
//		// clean up input for validation
//		for (int ii = 0; ii < noteGroups.size(); ii++) {
//			noteGroups.set(ii, noteGroups.get(ii).toLowerCase()); // make all notes lower case
//			noteGroups.set(ii, noteGroups.get(ii).replace(" ", "")); // remove all spaces
//			noteGroups.set(ii, noteGroups.get(ii).replace(",", "")); // remove all commas
//			// change all flats to equivalent sharps for ease of processing
//			noteGroups.set(ii, noteGroups.get(ii).replace("gb", "f#"));
//			noteGroups.set(ii, noteGroups.get(ii).replace("ab", "g#"));
//			noteGroups.set(ii, noteGroups.get(ii).replace("bb", "a#"));
//			noteGroups.set(ii, noteGroups.get(ii).replace("db", "c#"));
//			noteGroups.set(ii, noteGroups.get(ii).replace("eb", "d#"));
//			noteGroups.set(ii, noteGroups.get(ii).replace("gb", "f#"));
//		}

		// split up notes into different elements of new list for note validation
		ArrayList<String> allNotes = new ArrayList<>();
		for (String line : noteGroups) {
			// chord names and strings less than 3 characters (single notes) are grouped as
			// their own units
			if (line.matches(".*chord.*") || line.length() <= 3) {
				allNotes.add(line);
			}

			// multiple note chords (not names) are broken up for note validation
			else if (line.length() > 3) {
				ArrayList<Integer> numIndices = new ArrayList<>();
				for (int ii = 0; ii < line.length(); ii++) {
					if (Character.isDigit(line.charAt(ii))) {
						numIndices.add(ii);
					}
				}
				allNotes.add(line.substring(0, (numIndices.get(0)) + 1));
				for (int ii = 0; ii < (numIndices.size() - 1); ii++) {
					allNotes.add(line.substring((numIndices.get(ii) + 1), (numIndices.get(ii + 1)) + 1));
				}
			}
		}

		// loop through all notes and validate by comparing with reference note list and
		// chord map
		for (int ii = 0; ii < (allNotes.size()); ii++) {
			boolean match = false;
			if (chordMap.containsKey(allNotes.get(ii))) {
				match = true;
			}
			else if (guitarRange.contains(allNotes.get(ii))) {
				match = true;
			}
			if (match == false) {
				System.out.println("Note Range: " + guitarRangeOrig);
				System.out.println("Chord Range: " + chordMap.keySet());
				System.out.println();
				System.out.println("Input: " + allNotes);
				System.out.println("Problematic input: " + allNotes.get(ii));
				System.err.println(
						"Pitch mismatch! Please input pitches or chords within the range of a guitar with standard tuning as shown above:");
				System.exit(0);
				return;
			}
		}
	}

	private static LinkedHashMap<String, String> CreateChordMap() {
		LinkedHashMap<String, String> chordMap = new LinkedHashMap<String, String>() {
			{
				put("achord", "-02220");
				put("amaj7chord", "-02120");
				put("a7chord", "-02020");
				put("amchord", "-02210");
				put("am7chord", "-02010");

				put("bchord", "--4442");
				put("bmaj7chord", "22130-");
				put("b7chord", "-21202");
				put("bmchord", "--4432");
				put("bm7chord", "-20202");

				put("cchord", "-32010");
				put("cmaj7chord", "-32000");
				put("c7chord", "-32310");
				put("cmchord", "-310--");
				put("cm7chord", "-313--");

				put("dchord", "--0232");
				put("dmaj7chord", "--0222");
				put("d7chord", "--0212");
				put("dmchord", "--0231");
				put("dm7chord", "--0211");

				put("echord", "022100");
				put("emaj7chord", "021100");
				put("e7chord", "020100");
				put("emchord", "022000");
				put("em7chord", "022030");

				put("fchord", "--3211");
				put("fmaj7chord", "--3210");
				put("f7chord", "131211");
				put("fmchord", "--3111");
				put("fm7chord", "131111");

				put("gchord", "320003");
				put("gmaj7chord", "3-0002");
				put("g7chord", "320001");
				put("gmchord", "--0333");
				put("gm7chord", "-13030");
			}
		};
		return chordMap;
	}

	private static void recordChord(List<String> noteGroups, int ii) {
		// retrieve corresponding tab string for chord
		String chordTab = chordMap.get(noteGroups.get(ii));

		// add fret for each string to the string records
		if (Character.getNumericValue(chordTab.charAt(0))+transpose < 0) {
			elowRecord.add("?");
		}
		else {
			elowRecord.add("" + (Character.getNumericValue(chordTab.charAt(0))+transpose));
		}

		if (Character.getNumericValue(chordTab.charAt(1))+transpose < 0) {
			aRecord.add("?");
		}
		else {
			aRecord.add("" + (Character.getNumericValue(chordTab.charAt(1))+transpose));
		}

		if (Character.getNumericValue(chordTab.charAt(2))+transpose < 0) {
			dRecord.add("?");
		}
		else {
			dRecord.add("" + (Character.getNumericValue(chordTab.charAt(2))+transpose));
		}

		if (Character.getNumericValue(chordTab.charAt(3))+transpose < 0) {
			gRecord.add("?");
		}
		else {
			gRecord.add("" + (Character.getNumericValue(chordTab.charAt(3))+transpose));
		}

		if (Character.getNumericValue(chordTab.charAt(4))+transpose < 0) {
			bRecord.add("?");
		}
		else {
			bRecord.add("" + (Character.getNumericValue(chordTab.charAt(4))+transpose));
		}

		if (Character.getNumericValue(chordTab.charAt(5))+transpose < 0) {
			ehighRecord.add("?");
		}
		else {
			ehighRecord.add("" + (Character.getNumericValue(chordTab.charAt(5))+transpose));
		}
	}

	private static void recordSingleNote(List<String> noteGroups, int ii) {
		List<Integer> pitchStringFrets = Arrays.asList(100, 100, 100, 100, 100, 100); // Order e_h B G D A E_l
		List<Float> fretDeltaArray = Arrays.asList(0F, 0F, 0F, 0F, 0F, 0F);
		Float delta = 100F;
		boolean measureBreak = false;

		// loop through all frets and record positions where a pitch exists for each
		// string
		for (int stringFret = 0; stringFret < ehighString.size(); stringFret++) {
			if (noteGroups.get(ii).equals("")) {
				measureBreak = true;
			}
			if (noteGroups.get(ii).equals(ehighString.get(stringFret).toLowerCase())) {
				pitchStringFrets.set(0, stringFret); // the Ehigh string has index 0 in pitchStringPositions list
			}
			if (noteGroups.get(ii).equals(bString.get(stringFret).toLowerCase())) {
				pitchStringFrets.set(1, stringFret); // the B string has index 1 in pitchStringPositions list
			}
			if (noteGroups.get(ii).equals(gString.get(stringFret).toLowerCase())) {
				pitchStringFrets.set(2, stringFret); // the G string has index 2 in pitchStringPositions list
			}
			if (noteGroups.get(ii).equals(dString.get(stringFret).toLowerCase())) {
				pitchStringFrets.set(3, stringFret); // the D string has index 3 in pitchStringPositions list
			}
			if (noteGroups.get(ii).equals(aString.get(stringFret).toLowerCase())) {
				pitchStringFrets.set(4, stringFret); // the A string has index 4 in pitchStringPositions list
			}
			if (noteGroups.get(ii).equals(elowString.get(stringFret).toLowerCase())) {
				pitchStringFrets.set(5, stringFret); // the Elow string has index 5 in pitchStringPositions list
			}
		}

		// compare fret positions with lastFret to calculate delta (travel)
		for (int stringIndex = 0; stringIndex < pitchStringFrets.size(); stringIndex++) {
			delta = Math.abs(lastFret - pitchStringFrets.get(stringIndex));
			// easy to play open string
			if (pitchStringFrets.get(stringIndex) != 0) {
				fretDeltaArray.set(stringIndex, delta);
			}
		}

		// choose string that will play note based on lowest delta (travel) and record
		// to string records
		if (measureBreak == true) {
			ehighRecord.add("|");
			bRecord.add("|");
			gRecord.add("|");
			dRecord.add("|");
			aRecord.add("|");
			elowRecord.add("|");
			// pitchStringFrets.set(0, 0);
		}
		else if (Collections.min(pitchStringFrets) > ehighString.size()) { // if the beat notes are unplayable
			ehighRecord.add("?");
			bRecord.add("?");
			gRecord.add("?");
			dRecord.add("?");
			aRecord.add("?");
			elowRecord.add("?");
		}
		else if (fretDeltaArray.indexOf(Collections.min(fretDeltaArray)) == 0) {
			ehighRecord.add(pitchStringFrets.get(fretDeltaArray.indexOf(Collections.min(fretDeltaArray))).toString());
			bRecord.add("-");
			gRecord.add("-");
			dRecord.add("-");
			aRecord.add("-");
			elowRecord.add("-");
		}
		else if (fretDeltaArray.indexOf(Collections.min(fretDeltaArray)) == 1) {
			ehighRecord.add("-");
			bRecord.add(pitchStringFrets.get(fretDeltaArray.indexOf(Collections.min(fretDeltaArray))).toString());
			gRecord.add("-");
			dRecord.add("-");
			aRecord.add("-");
			elowRecord.add("-");
		}
		else if (fretDeltaArray.indexOf(Collections.min(fretDeltaArray)) == 2) {
			ehighRecord.add("-");
			bRecord.add("-");
			gRecord.add(pitchStringFrets.get(fretDeltaArray.indexOf(Collections.min(fretDeltaArray))).toString());
			dRecord.add("-");
			aRecord.add("-");
			elowRecord.add("-");
		}
		else if (fretDeltaArray.indexOf(Collections.min(fretDeltaArray)) == 3) {
			ehighRecord.add("-");
			bRecord.add("-");
			gRecord.add("-");
			dRecord.add(pitchStringFrets.get(fretDeltaArray.indexOf(Collections.min(fretDeltaArray))).toString());
			aRecord.add("-");
			elowRecord.add("-");
		}
		else if (fretDeltaArray.indexOf(Collections.min(fretDeltaArray)) == 4) {
			ehighRecord.add("-");
			bRecord.add("-");
			gRecord.add("-");
			dRecord.add("-");
			aRecord.add(pitchStringFrets.get(fretDeltaArray.indexOf(Collections.min(fretDeltaArray))).toString());
			elowRecord.add("-");
		}
		else if (fretDeltaArray.indexOf(Collections.min(fretDeltaArray)) == 5) {
			ehighRecord.add("-");
			bRecord.add("-");
			gRecord.add("-");
			dRecord.add("-");
			aRecord.add("-");
			elowRecord.add(pitchStringFrets.get(fretDeltaArray.indexOf(Collections.min(fretDeltaArray))).toString());
		}

		// set new lastFret to the current fret
		if (measureBreak == false) {
			lastFret = Collections.min(pitchStringFrets);
		}
	}

	private static void recordMultiNote(List<String> noteGroups, int ii) {
		ArrayList<String> chordNotes = new ArrayList<>();
		ArrayList<Integer> allPlayableStrings = new ArrayList<>();
		ArrayList<List<Integer>> allStringFrets = new ArrayList<>();

		// split up notes based on numbers
		ArrayList<Integer> numIndices = new ArrayList<>();
		for (int yy = 0; yy < noteGroups.get(ii).length(); yy++) {
			if (Character.isDigit(noteGroups.get(ii).charAt(yy))) {
				numIndices.add(yy);
			}
		}
		chordNotes.add(noteGroups.get(ii).substring(0, (numIndices.get(0)) + 1));
		for (int yy = 0; yy < (numIndices.size() - 1); yy++) {
			chordNotes.add(noteGroups.get(ii).substring((numIndices.get(yy) + 1), (numIndices.get(yy + 1)) + 1));
		}

		// loop through all frets and record positions where a pitch exists for each
		// string
		for (int yy = 0; yy < chordNotes.size(); yy++) {
			List<Integer> pitchStringFrets = Arrays.asList(100, 100, 100, 100, 100, 100);
			for (int stringFret = 0; stringFret < ehighString.size(); stringFret++) {
				if (chordNotes.get(yy).equals(ehighString.get(stringFret).toLowerCase())) {
					// the Ehigh string has index 0 in pitchStringPositions list
					pitchStringFrets.set(0, stringFret);
				}
				if (chordNotes.get(yy).equals(bString.get(stringFret).toLowerCase())) {
					// the B string has index 1 in pitchStringPositions list
					pitchStringFrets.set(1, stringFret);
				}
				if (chordNotes.get(yy).equals(gString.get(stringFret).toLowerCase())) {
					// the G string has index 2 in pitchStringPositions list
					pitchStringFrets.set(2, stringFret);
				}
				if (chordNotes.get(yy).equals(dString.get(stringFret).toLowerCase())) {
					// the D string has index 3 in pitchStringPositions list
					pitchStringFrets.set(3, stringFret);
				}
				if (chordNotes.get(yy).equals(aString.get(stringFret).toLowerCase())) {
					// the A string has index 4 in pitchStringPositions list
					pitchStringFrets.set(4, stringFret);
				}
				if (chordNotes.get(yy).equals(elowString.get(stringFret).toLowerCase())) {
					// the Elow string has index 5 in pitchStringPositions list
					pitchStringFrets.set(5, stringFret);
				}
			}

			// calculate number of playable strings
			int playableStrings = 0;
			for (int fret : pitchStringFrets) {
				if (fret != 100) {
					playableStrings++;
				}
			}
			allStringFrets.add(pitchStringFrets);
			allPlayableStrings.add(playableStrings);
		}

		// the playable notes product helps determine the size of the chord matrix
		int playableNotesProduct = 1;
		for (int notePlayableStrings : allPlayableStrings) {
			playableNotesProduct *= notePlayableStrings;
		}

		// creating chord matrix and initializing all values to 100
		int[][] chordMatrix = new int[playableNotesProduct - chordNotes.size()][chordNotes.size() + 4];
		for (int column = 0; column < (chordNotes.size() + 4); column++) {
			int rowIndex = 0;
			for (int[] row : chordMatrix) {
				chordMatrix[rowIndex][column] = 100;
				rowIndex++;
			}
		}

		// add the playable frets for note 1 to the chord matrix
		int loopNote1Counter = 0;
		for (int yy = 0; yy < allStringFrets.get(0).size(); yy++) {
			if ((int) allStringFrets.get(0).get(yy) != 100) {
				for (int qq = 0; qq < (allStringFrets.get(1).size()); qq++) {
					if ((int) allStringFrets.get(1).get(qq) != 100) {
						if (qq != yy) {
							chordMatrix[loopNote1Counter][0] = (int) allStringFrets.get(0).get(yy);
							loopNote1Counter++;
						}
					}
				}
			}
		}

		// add the playable frets for note 2 to the chord matrix
		int loopNote2Counter = 0;
		for (int yy = 0; yy < allStringFrets.get(0).size(); yy++) {
			if ((int) allStringFrets.get(0).get(yy) != 100) {
				for (int qq = 0; qq < allStringFrets.get(1).size(); qq++) {
					if ((int) allStringFrets.get(allStringFrets.size() - 1).get(qq) != 100) {
						if (qq != yy) {
							chordMatrix[loopNote2Counter][1] = (int) allStringFrets.get((allStringFrets.size() - 1))
									.get(qq);
							loopNote2Counter++;
						}
					}
				}
			}
		}

		for (int yy = 0; yy < chordMatrix.length; yy++) {
			// calculate difference between note fret positions (stretch) average note fret
			// position
			chordMatrix[yy][chordNotes.size()] = Math.abs(chordMatrix[yy][0] - chordMatrix[yy][1]);

			// calculate average note fret position
			chordMatrix[yy][chordNotes.size() + 1] = (chordMatrix[yy][0] + chordMatrix[yy][1]) / 2;

			// calculate average note fret position - lastFret (travel)
			chordMatrix[yy][chordNotes.size() + 2] = Math
					.abs(chordMatrix[yy][chordNotes.size() + 1] - Math.round(lastFret));

			// calculate stretch plus travel (difficulty factor)
			chordMatrix[yy][chordNotes.size() + 3] = chordMatrix[yy][chordNotes.size()]
					+ chordMatrix[yy][chordNotes.size() + 2];
		}

		// identify the fret combination will the lowest difficulty factor
		Integer[] chordNoteDifficulties = new Integer[chordMatrix.length];
		for (int yy = 0; yy < chordMatrix.length; yy++) { // loop through the 6 array rows
			chordNoteDifficulties[yy] = (int) chordMatrix[yy][chordNotes.size() + 3];
		}
		int bestChordIndex = Arrays.asList(chordNoteDifficulties)
				.indexOf(Collections.min(Arrays.asList(chordNoteDifficulties)));

		// create analogous list with to record beat frets
		int[] beatFrets = { 100, 100, 100, 100, 100, 100 };
		beatFrets[allStringFrets.get(0).indexOf(chordMatrix[bestChordIndex][0])] = chordMatrix[bestChordIndex][0];
		beatFrets[allStringFrets.get(1).indexOf(chordMatrix[bestChordIndex][1])] = chordMatrix[bestChordIndex][1];

		// record beat frets into string records
		if (beatFrets[0] != 100) {
			ehighRecord.add("" + beatFrets[0]);
		}
		else if (beatFrets[0] == 100) {
			ehighRecord.add("-");
		}
		if (beatFrets[1] != 100) {
			bRecord.add("" + beatFrets[1]);
		}
		else if (beatFrets[1] == 100) {
			bRecord.add("-");
		}
		if (beatFrets[2] != 100) {
			gRecord.add("" + beatFrets[2]);
		}
		else if (beatFrets[2] == 100) {
			gRecord.add("-");
		}
		if (beatFrets[3] != 100) {
			dRecord.add("" + beatFrets[3]);
		}
		else if (beatFrets[3] == 100) {
			dRecord.add("-");
		}
		if (beatFrets[4] != 100) {
			aRecord.add("" + beatFrets[4]);
		}
		else if (beatFrets[4] == 100) {
			aRecord.add("-");
		}
		if (beatFrets[5] != 100) {
			elowRecord.add("" + beatFrets[5]);
		}
		else if (beatFrets[5] == 100) {
			elowRecord.add("-");
		}

		// set new lastFret to the current fret
		lastFret = chordMatrix[bestChordIndex][chordNotes.size() + 1];
	}

	private static void outputTabToFile(String sourceFileName) {
		
		String tranposeFileNameMod;
		if (transpose == 0) {
			tranposeFileNameMod = "";
		}
		else {
			tranposeFileNameMod = "transposed_" + transpose + "_";
		}
		
		String outputFileName = "tab_" + tranposeFileNameMod + sourceFileName;
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(outputFileName));
			System.out.println();

			writer.write(tunedStrings.get(0) + ": " + ehighRecord);
			writer.newLine();
			writer.write(tunedStrings.get(1) + ": " + bRecord);
			writer.newLine();
			writer.write(tunedStrings.get(2) + ": " + gRecord);
			writer.newLine();
			writer.write(tunedStrings.get(3) + ": " + dRecord);
			writer.newLine();
			writer.write(tunedStrings.get(4) + ": " + aRecord);
			writer.newLine();
			writer.write(tunedStrings.get(5) + ": " + elowRecord);
			writer.newLine();
			writer.newLine();
			writer.newLine();

			System.out.println();

			int outputRowLength = 20;
			boolean multipleMeasureBreak = false;
			while (!ehighRecord.isEmpty()) {
				System.out.print(tunedStrings.get(0) + ": ");
				writer.write(tunedStrings.get(0) + ": ");
				for (int ii = 0; ii < outputRowLength; ii++) {
					if (!ehighRecord.isEmpty()) {
						if (multipleMeasureBreak == false && ehighRecord.get(0) == "|" && ehighRecord.get(1) == "|") {
							System.out.print("-" + ehighRecord.get(0));
							writer.write("-" + ehighRecord.get(0));
							multipleMeasureBreak = true;
						}
						else if (multipleMeasureBreak == true && ehighRecord.get(1) == "|") { // if the previous output
																								// was "|" as well as
																								// the next output
							System.out.print(ehighRecord.get(0));
							writer.write(ehighRecord.get(0));
						}
						else if (multipleMeasureBreak == true && ehighRecord.get(1) != "|") { // if the previous output
																								// was "|" but not the
																								// next output
							System.out.print(ehighRecord.get(0) + "-");
							writer.write(ehighRecord.get(0) + "-");
							multipleMeasureBreak = false;
						}
						else {
							System.out.print("-" + ehighRecord.get(0) + "-");
							writer.write("-" + ehighRecord.get(0) + "-");
						}
						ehighRecord.remove(0);
					}
				}
				System.out.print("\n" + tunedStrings.get(1) + ": ");
				writer.write("\n" + tunedStrings.get(1) + ": ");
				for (int ii = 0; ii < outputRowLength; ii++) {
					if (!bRecord.isEmpty()) {
						if (multipleMeasureBreak == false && bRecord.get(0) == "|" && bRecord.get(1) == "|") {
							System.out.print("-" + bRecord.get(0));
							writer.write("-" + bRecord.get(0));
							multipleMeasureBreak = true;
						}
						else if (multipleMeasureBreak == true && bRecord.get(1) == "|") { // if the previous output was
																							// "|" as well as the next
																							// output
							System.out.print(bRecord.get(0));
							writer.write(bRecord.get(0));
						}
						else if (multipleMeasureBreak == true && bRecord.get(1) != "|") { // if the previous output was
																							// "|" but not the next
																							// output
							System.out.print(bRecord.get(0) + "-");
							writer.write(bRecord.get(0) + "-");
							multipleMeasureBreak = false;
						}
						else {
							System.out.print("-" + bRecord.get(0) + "-");
							writer.write("-" + bRecord.get(0) + "-");
						}
						bRecord.remove(0);
					}
				}
				System.out.print("\n" + tunedStrings.get(2) + ": ");
				writer.write("\n" + tunedStrings.get(2) + ": ");
				for (int ii = 0; ii < outputRowLength; ii++) {
					if (!gRecord.isEmpty()) {
						if (multipleMeasureBreak == false && gRecord.get(0) == "|" && gRecord.get(1) == "|") {
							System.out.print("-" + gRecord.get(0));
							writer.write("-" + gRecord.get(0));
							multipleMeasureBreak = true;
						}
						else if (multipleMeasureBreak == true && gRecord.get(1) == "|") { // if the previous output was
																							// "|" as well as the next
																							// output
							System.out.print(gRecord.get(0));
							writer.write(gRecord.get(0));
						}
						else if (multipleMeasureBreak == true && gRecord.get(1) != "|") { // if the previous output was
																							// "|" but not the next
																							// output
							System.out.print(gRecord.get(0) + "-");
							writer.write(gRecord.get(0) + "-");
							multipleMeasureBreak = false;
						}
						else {
							System.out.print("-" + gRecord.get(0) + "-");
							writer.write("-" + gRecord.get(0) + "-");
						}
						gRecord.remove(0);
					}
				}
				System.out.print("\n" + tunedStrings.get(3) + ": ");
				writer.write("\n" + tunedStrings.get(3) + ": ");
				for (int ii = 0; ii < outputRowLength; ii++) {
					if (!dRecord.isEmpty()) {
						if (multipleMeasureBreak == false && dRecord.get(0) == "|" && dRecord.get(1) == "|") {
							System.out.print("-" + dRecord.get(0));
							writer.write("-" + dRecord.get(0));
							multipleMeasureBreak = true;
						}
						else if (multipleMeasureBreak == true && dRecord.get(1) == "|") { // if the previous output was
																							// "|" as well as the next
																							// output
							System.out.print(dRecord.get(0));
							writer.write(dRecord.get(0));
						}
						else if (multipleMeasureBreak == true && dRecord.get(1) != "|") { // if the previous output was
																							// "|" but not the next
																							// output
							System.out.print(dRecord.get(0) + "-");
							writer.write(dRecord.get(0) + "-");
							multipleMeasureBreak = false;
						}
						else {
							System.out.print("-" + dRecord.get(0) + "-");
							writer.write("-" + dRecord.get(0) + "-");
						}
						dRecord.remove(0);
					}
				}
				System.out.print("\n" + tunedStrings.get(4) + ": ");
				writer.write("\n" + tunedStrings.get(4) + ": ");
				for (int ii = 0; ii < outputRowLength; ii++) {
					if (!aRecord.isEmpty()) {
						if (multipleMeasureBreak == false && aRecord.get(0) == "|" && aRecord.get(1) == "|") {
							System.out.print("-" + aRecord.get(0));
							writer.write("-" + aRecord.get(0));
							multipleMeasureBreak = true;
						}
						else if (multipleMeasureBreak == true && aRecord.get(1) == "|") { // if the previous output was
																							// "|" as well as the next
																							// output
							System.out.print(aRecord.get(0));
							writer.write(aRecord.get(0));
						}
						else if (multipleMeasureBreak == true && aRecord.get(1) != "|") { // if the previous output was
																							// "|" but not the next
																							// output
							System.out.print(aRecord.get(0) + "-");
							writer.write(aRecord.get(0) + "-");
							multipleMeasureBreak = false;
						}
						else {
							System.out.print("-" + aRecord.get(0) + "-");
							writer.write("-" + aRecord.get(0) + "-");
						}
						aRecord.remove(0);
					}
				}
				System.out.print("\n" + tunedStrings.get(5) + ": ");
				writer.write("\n" + tunedStrings.get(5) + ": ");
				for (int ii = 0; ii < outputRowLength; ii++) {
					if (!elowRecord.isEmpty()) {
						if (multipleMeasureBreak == false && elowRecord.get(0) == "|" && elowRecord.get(1) == "|") {
							System.out.print("-" + elowRecord.get(0));
							writer.write("-" + elowRecord.get(0));
							multipleMeasureBreak = true;
						}
						else if (multipleMeasureBreak == true && elowRecord.get(1) == "|") { // if the previous output
																								// was "|" as well as
																								// the next output
							System.out.print(elowRecord.get(0));
							writer.write(elowRecord.get(0));
						}
						else if (multipleMeasureBreak == true && elowRecord.get(1) != "|") { // if the previous output
																								// was "|" but not the
																								// next output
							System.out.print(elowRecord.get(0) + "-");
							writer.write(elowRecord.get(0) + "-");
							multipleMeasureBreak = false;
						}
						else {
							System.out.print("-" + elowRecord.get(0) + "-");
							writer.write("-" + elowRecord.get(0) + "-");
						}
						elowRecord.remove(0);
					}
				}

				// output two new lines
				System.out.println();
				System.out.println();
				writer.newLine();
				writer.newLine();
			}

			System.out.println();

			writer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
