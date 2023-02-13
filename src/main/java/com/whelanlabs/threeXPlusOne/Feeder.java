package com.whelanlabs.threeXPlusOne;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Feeder {

	private FileWriter _myWriter = null;
	private static final Logger logger = LogManager.getLogger(Feeder.class);
	private String _filename = null;

	public Feeder(String generation) throws IOException {
		_filename = "C:/_temp/tnpo/generation_" + generation + ".txt";
		_myWriter = new FileWriter(_filename);
		// myWriter.write("Files in Java might be tricky, but it is fun enough!");
		// myWriter.close();
	}

	public void add(List<List<Integer>> contents) throws IOException {
		for (List<Integer> content : contents) {
			_myWriter.write(content.toString());
		}
	}

	public void closeWriter() throws IOException {
		_myWriter.close();
	}

	public synchronized List<Integer> get() {

		if() {
			FileReader _fileReader = new FileReader(
		            "C:\\Users\\pankaj\\Desktop\\test.txt");
		}
		return null;
	}

}
