package edu.tkr.ai.chatboat.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class QueryService {

	public static Set<String> getQueryData(String query)
	{
		Set<String> words=new HashSet<String>();

		WordNetDatabase database = null;

		System.setProperty("wordnet.database.dir",
				"C:\\Program Files (x86)\\WordNet\\2.1\\dict\\");

		database = WordNetDatabase.getFileInstance();

		List<String> postList=new ArrayList<String>();

		String[] posts=query.split(" ");

		for(String ps:posts)
		{
			postList.add(ps);
		}

		List<String> stopWords=stopList();

		Iterator<String> sit=stopWords.iterator();

		while(sit.hasNext()) {

			String stopWord=sit.next();

			postList.remove(stopWord);
		}

		Iterator<String> it=postList.iterator();

		while(it.hasNext()) {

			String split=it.next();

			if(split.length()>3)
			{
				words.add(split);
			}

			Synset[] synsets = database.getSynsets(split, SynsetType.NOUN);

			for (int i = 0; i < synsets.length; i++) 
			{
				String[] wordForms = synsets[i].getWordForms();

				for (int j = 0; j < wordForms.length; j++) {

					String[] splits = wordForms[j].split(" ");

					for (int l = 0; l < splits.length; l++) {

						String keyword = splits[l];

						if(split.length()>=3)
						{
							words.add(keyword);
						}
					}//for

				}//for
			}
		}

		return words;
	}

	public static int getRank(Set<String> words,Set<String> questionWords)
	{
		int frequency=0;

		Iterator<String> it=questionWords.iterator();

		while(it.hasNext())
		{
			String qword=it.next();

			frequency=frequency+Collections.frequency(words,qword);
		}

		return frequency;
	}

	public static List<String> stopList()
	{
		List<String> stopList=new ArrayList<String>();

		try {

			File file = new File("C:\\Users\\nikhil nick\\Documents\\workspace-sts-3.6.3.SR1\\ArtificialChatBoat\\src\\edu\\tkr\\ai\\chatboat\\service\\stoplist.txt");

			FileReader fileReader = new FileReader(file);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;

			while ((line = bufferedReader.readLine()) != null) {

				stopList.add(line);
			}

			fileReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return stopList;
	}
}
