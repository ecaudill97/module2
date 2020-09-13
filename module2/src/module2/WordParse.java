//WordParse Eric Caudill 9/11/2020 Module2

package module2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.*;



public class WordParse {

	public static void main(String[] args) throws IOException {
		
		// asks for and reads in the website url
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a Url");
		String html = scan.nextLine();
		
		// uses jsoup library to get the html as text, i only get the text from the 
		// h1,h3,h4,and p html sections because that is where the poem is located.
		
		Document doc = Jsoup.connect(html).get();
		Elements h1= doc.select("h1");
		Elements h3=doc.select("h3");
		Elements h4=doc.select("h4");
		Elements p=doc.select("p");
		
		// create a string of the entire poem, creates an array of strings, then creates a list
		// from the array using the aslist function
		
		String poem = h1.text() + " " +  h3.text() + " " + h4.text() + " " + p.text();
		String[] words = poem.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
		List<String> wordList = Arrays.asList(words);
		
		// creates a map of the words using the wordlist and assigning words a key which equals
		// the amount of times its been used.
		
		Map<String, Long> map = wordList.stream()
				.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		
		
		// this orders the map and sorts it in descending order
		
		List<Map.Entry<String, Long>> result = map.entrySet().stream()
		        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		        .collect(Collectors.toList());
		
		
		// creates a list of the words in the map that are now order by greatest use to least
		
		String output = result.toString();
		String topwords[] = output.replaceAll("\\[|\\]|","").split("\\s+");
		List<String> foo = Arrays.asList(topwords);
		
		
		// prints this list
		
		System.out.println("Most commonly used words in text: ");
		System.out.println("-----------------------------------");
		int i = 1;
		for(String s : foo)
		{
			System.out.println(i+". "+ s);
			i++;
		}
		
		// closing scanner
		
		scan.close();
		
		
	}
	
}
