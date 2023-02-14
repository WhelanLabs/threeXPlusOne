package com.whelanlabs.threeXPlusOne;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Feeder {

	private FileWriter _myWriter = null;
	private static final Logger logger = LogManager.getLogger(Feeder.class);
	private String _filename = null;
	private BufferedReader _br = null;
	private Integer _size = 0;

	public Feeder(String generation) throws IOException {
		_filename = "C:/_temp/tnpo/generation_" + generation + ".txt";
		_myWriter = new FileWriter(_filename);
	}

	public void add(List<List<Integer>> contents) {
		try {
			for (List<Integer> content : contents) {
				_myWriter.write(content.toString().replace("[", "").replace("]", "") + "\n");
			}
			_myWriter.flush();
		} catch (IOException e) {
			throw new RuntimeException("ADD gone bad", e);
		}
		_size += contents.size();
	}

	public void closeWriter() throws IOException {
		_myWriter.close();
	}

	public synchronized List<List<Integer>> get(Integer batchSize) throws IOException {
		List<List<Integer>> results = new ArrayList<>();

		if (null == _br) {
			FileReader _fileReader = new FileReader(_filename);
			_br = new BufferedReader(_fileReader);
		}

		String st;
		int i = 0;
		while ((i < batchSize) && (st = _br.readLine()) != null) {
			i++;
			
			List<String> stringList = Arrays.asList(st.split(","));

	        List<Integer> newList = new ArrayList<>();
	        for (String s : stringList) {
	        	s = s.replaceFirst(" ", "");
	            newList.add(Integer.parseInt(s));
	        }
	        
			results.add(newList);
		}

		return results;
	}

	public Integer size() {
		return _size;
	}

}
