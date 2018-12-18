import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//$Id$

public class InputOutputComparison {
	public static void main(String[] args){
		try {
			List<String> output = loadFileIntoCollection(new File("/Users/gokul-4406/Downloads/inputSuggestion.txt"));
//			List<String> expectedOutput = loadFileIntoCollection(new File("/Users/gokul-4406/Downloads/expectedOutputSuggestion.txt"));
//			for(int i = 0;i < output.size();i++){
//				if(!output.get(i).equals(expectedOutput.get(i))){
//					System.out.println(i);
//					System.out.println("Expected->"+expectedOutput.get(i));
//					System.out.println("Found->"+output.get(i));
//				}
//			}
//			output.sort(new Comparator<String>() {
//	      	      public int compare(String o1, String o2) {
//	    	          return o1.compareTo(o2);
//	    	        }
//	    	        });
			for(String str:output){
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static List<String> loadFileIntoCollection(File file) throws IOException{
		List<String> list = new ArrayList<>();
		try(Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))){
			list = stream.filter(line->line.startsWith("u") || line.startsWith("1")).map(String::toLowerCase).collect(Collectors.toList());
		}catch(IOException e){
			throw e;
		}
		return list;
	}
}

