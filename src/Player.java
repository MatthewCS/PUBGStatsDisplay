import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class Player {

	private JSONObject player;
	private ArrayList al;
	private String playerID;
	private final Constants c = new Constants();

	public Player(String name) {
		try{
			URL url = new URL("https://api.playbattlegrounds.com/shards/pc-na/players?filter[playerNames]=" + name);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization","Bearer " + c.getKey());
			conn.setRequestProperty("Accept", "application/vnd.api+json");

			InputStream stream = conn.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			JSONParser jsonParser = new JSONParser();
			JSONObject obj = (JSONObject) jsonParser.parse(reader);
			JSONArray array = (JSONArray) obj.get("data");
			//System.out.println(array.size());
			player = (JSONObject) array.get(0);
			//System.out.println(player);
			playerID = (String)player.get("id");
		}
		catch(Exception e) {
			if(c.DEBUG_ENABLED)
				e.printStackTrace();
		}
		al = new ArrayList<JSONObject>();
        try {
	        JSONObject rel = (JSONObject)player.get("relationships");
	        JSONObject matches = (JSONObject)rel.get("matches");
	        JSONArray matchesArray = (JSONArray)matches.get("data");

	        for(int i = 0; i < matchesArray.size(); i++) {
	        	JSONObject singleMatch = (JSONObject)matchesArray.get(i);
	        	String id = (String)singleMatch.get("id");
	        	URL urlMatch = new URL("https://api.playbattlegrounds.com/shards/pc-na/matches/" + id);
	        	HttpURLConnection connMatch = (HttpURLConnection) urlMatch.openConnection();
	        	connMatch.setRequestMethod("GET");
	        	connMatch.setRequestProperty("Authorization", "Bearer " + c.getKey());
	        	connMatch.setRequestProperty("Accept", "application/vnd.api+json");
	        	InputStream streamMatch = connMatch.getInputStream();
	        	InputStreamReader readerMatch = new InputStreamReader(streamMatch);
	        	JSONParser jsonParserMatch = new JSONParser();
	        	JSONObject objMatch = (JSONObject) jsonParserMatch.parse(readerMatch);
	        	al.add(objMatch);
	        }
	    }
        catch(Exception e) {
            al = null;
        }
	}

	public int getAvgTime() {
		int totalTime = 0;
		int numMatches = al.size();
		for(int i = 0; i < al.size(); i++) {
			JSONObject match = (JSONObject)al.get(i);
			JSONObject data = (JSONObject)match.get("data");
			JSONObject att = (JSONObject)data.get("attributes");
			totalTime += (int)((long)att.get("duration"));
		}

		return totalTime / numMatches;
	}

	public int getAvgRanking() {
		int totalRank = 0;
		int numRankings = 0;
		for(int i = 0; i < al.size(); i++) {
			JSONObject match = (JSONObject)al.get(i);
			JSONArray included = (JSONArray)match.get("included");
			for(int j = 0; j < included.size(); j++) {
				JSONObject obj = (JSONObject)included.get(j);
				if(((String)obj.get("type")).equals("participant")) {	//solo, not team
					JSONObject att = (JSONObject)obj.get("attributes");
					JSONObject stats = (JSONObject)att.get("stats");
					String id = (String)stats.get("playerId");
					if(id.equals(playerID)) {
						numRankings++;
						totalRank += (int)((long)stats.get("winPlace"));
					}
				}
			}
		}
		if(numRankings == 0) {
			return 0;
		}
		return totalRank/numRankings;
	}

	public String getName() {
		JSONObject att = (JSONObject)player.get("attributes");
		String name = (String)att.get("name");
		return name;
	}
	public String getID() {
		return playerID;
	}

	public int numRecent() {
		return al.size();
	}
}
