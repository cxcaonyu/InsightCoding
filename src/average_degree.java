import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// example of program that calculates the average degree of hashtags

public class average_degree {

	
	static List<Tweet> readFrom (String input) throws IOException{
		List<Tweet> tweets=new ArrayList<Tweet>();
		return tweets;
	}
	static List<Integer> averageUpdate(List<Tweet> tweets){
		List<Integer> res = new ArrayList<Integer>();
		Map<String, Set<String>> adjList = new HashMap<String, Set<String>>();
		LinkedList<Tweet> queue = new LinkedList<Tweet>();
		for(int i=0; i<tweets.size();i++){
			Tweet current = tweets.get(i);
			List<String> hashtags = current.hashtags;
			int ave = 0;
			if(queue.isEmpty() || minus(current.time,queue.getFirst().time)<60){
				int size = hashtags.size();
				for(int j=0; j<hashtags.size()-1; j++){
					if(!adjList.containsKey(hashtags.get(j)))
						adjList.put(hashtags.get(j), new HashSet<String>());
					if(!adjList.get(hashtags.get(j)).contains(hashtags.get(j+1)))
						adjList.get(hashtags.get(j)).add(hashtags.get(j+1));
					if(!adjList.containsKey(hashtags.get(j+1)))
						adjList.put(hashtags.get(j+1), new HashSet<String>());
					if(!adjList.get(hashtags.get(j+1)).contains(hashtags.get(j)))
						adjList.get(hashtags.get(j+1)).add(hashtags.get(j));
				}
				if(!adjList.containsKey(hashtags.get(size-1)))
					adjList.put(hashtags.get(size-1), new HashSet<String>());
				if(!adjList.get(hashtags.get(size-1)).contains(hashtags.get(0)))
					adjList.get(hashtags.get(size-1)).add(hashtags.get(0));
				for(Map.Entry<String,Set<String>> entry : adjList.entrySet()){
					ave+=entry.getValue().size();
				}
				ave = ave/adjList.size();
				res.add(ave);
			}
			else {
				while(minus(current.time, queue.getFirst().time)>60){
					//...
					List<String> remove = queue.removeFirst().hashtags;
					for(int j=0; j<remove.size()-1;j++){
						if(adjList.containsKey(remove.get(j))){
							if(adjList.get(remove.get(j)).contains(remove.get(j+1)))
								adjList.get(remove.get(j)).remove(remove.get(j+1));
						}
						if(adjList.containsKey(remove.get(j+1))){
							if(adjList.get(remove.get(j+1)).contains(remove.get(j)))
								adjList.get(remove.get(j+1)).remove(remove.get(j));
						}
					}
					int size = remove.size();
					if(adjList.containsKey(remove.get(size-1))){
						if(adjList.get(remove.get(size-1)).contains(remove.get(0)))
							adjList.get(remove.get(size-1)).remove(remove.get(0));
					}
					if(adjList.containsKey(remove.get(0))){
						if(adjList.get(remove.get(0)).contains(remove.get(size-1)))
							adjList.get(remove.get(0)).remove(remove.get(size-1));
					}
				}
				for(Map.Entry<String,Set<String>> entry : adjList.entrySet()){
					ave+=entry.getValue().size();
				}
				ave = ave/adjList.size();
				res.add(ave);
				
			}
		}
		
		return res;
	}
	static void writeTo(String output, List<Tweet> tweets){
		
	}
	static int minus (String time1, String time2){
		return 0;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		String input = "";
		String output = "";
		List<Tweet> tweets = readFrom(input);
		List<Integer> res = averageUpdate(tweets);   
		writeTo(output, tweets);
		
		for(int i=0; i<res.size();i++)
			System.out.println(res.get(i));
	}

}
