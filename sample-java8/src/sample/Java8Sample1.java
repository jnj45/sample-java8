package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * 
 * @author Administrator
 *
 */
public class Java8Sample1 {

	/**
	 * 파일을 읽어 유니크한 단어들을 출력
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// 기존 코딩
		try(final FileReader fileReader = new FileReader(new File("c:/temp/test.txt"));
			final BufferedReader bufferedReader = new BufferedReader(fileReader);	
				){
			
			final List<String> uniqueWords = new ArrayList<String>();
			String line = bufferedReader.readLine();
			while(line != null){
				final String[] words = line.split("[\\s]+");
				for(String word : words){
					if (!uniqueWords.contains(word)){
						uniqueWords.add(word);
					}
				}
				line = bufferedReader.readLine();
			}
			Collections.sort(uniqueWords);
			System.out.println(uniqueWords);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// Java8 버전 코딩
		try(final Stream<String> lines = Files.lines(Paths.get("c:/temp/test.txt"))){
			System.out.println(
				lines
					.map(line -> line.split("[\\s]+"))
					.flatMap(Arrays::stream)
					.distinct()
					.sorted()
//					.collect(toList())
			);
		}
	}

}
