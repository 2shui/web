package com.shui.recommend;

import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Recommend {
	/**
	 * 返回最近距离用户
	 * 
	 * */
	public Map<Long, Double> computeNearestNeighbor() {
		return null;
	}
	
	
	/**
	 * 返回距离
	 * */
	private Double pearson(JsonObject rate1, JsonObject rate2) {
		Double sum_xy = 0D;
		Double sum_x = 0D;
		Double sum_y = 0D;
		Double sum_x2 = 0D;
		Double sum_y2 = 0D;
		Double n = 0D;
		
//		JsonArray array = rate2.getAsJsonArray();
		
		for(Entry<String, JsonElement> o : rate1.entrySet()){
//			Iterator<JsonElement> it = array.iterator();
//			while(it.hasNext()) {
//				JsonObject obj = it.next().getAsJsonObject();
//				if(obj.has(o.getKey())) {
//					n += 1;
//					Double x = o.getValue().getAsDouble();
//					Double y = rate2.get(o.getKey()).getAsDouble();
//					sum_xy += x*y;
//					sum_x += x;
//					sum_y += y;
//					sum_x2 += x*x;
//					sum_y2 += y*y;
//				}
//			}
			
			if(rate2.has(o.getKey())) {
				n += 1;
				Double x = o.getValue().getAsDouble();
				Double y = rate2.get(o.getKey()).getAsDouble();
				sum_xy += x*y;
				sum_x += x;
				sum_y += y;
				sum_x2 += x*x;
				sum_y2 += y*y;
			}
		}
		if(0 == n) {
			return 0D;
		}else{
			Double sx = Math.sqrt(sum_x2 - (Math.pow(sum_x, 2)/n));
			Double sy = Math.sqrt(sum_y2 - (Math.pow(sum_y, 2)/n));
			if(0!=sx && 0!=sy){
				return (sum_xy-sum_x*sum_y/n)/sx/sy;
			}else{
				return 0D;
			}
		}
	}
	/**
	 
	 users = {"Angelica": {"Blues Traveler": 3.5, "Broken Bells": 2.0, "Norah Jones": 4.5, "Phoenix": 5.0, "Slightly Stoopid": 1.5, "The Strokes": 2.5, "Vampire Weekend": 2.0},
         "Bill":{"Blues Traveler": 2.0, "Broken Bells": 3.5, "Deadmau5": 4.0, "Phoenix": 2.0, "Slightly Stoopid": 3.5, "Vampire Weekend": 3.0},
         "Chan": {"Blues Traveler": 5.0, "Broken Bells": 1.0, "Deadmau5": 1.0, "Norah Jones": 3.0, "Phoenix": 5, "Slightly Stoopid": 1.0},
         "Dan": {"Blues Traveler": 3.0, "Broken Bells": 4.0, "Deadmau5": 4.5, "Phoenix": 3.0, "Slightly Stoopid": 4.5, "The Strokes": 4.0, "Vampire Weekend": 2.0},
         "Hailey": {"Broken Bells": 4.0, "Deadmau5": 1.0, "Norah Jones": 4.0, "The Strokes": 4.0, "Vampire Weekend": 1.0},
         "Jordyn":  {"Broken Bells": 4.5, "Deadmau5": 4.0, "Norah Jones": 5.0, "Phoenix": 5.0, "Slightly Stoopid": 4.5, "The Strokes": 4.0, "Vampire Weekend": 4.0},
         "Sam": {"Blues Traveler": 5.0, "Broken Bells": 2.0, "Norah Jones": 3.0, "Phoenix": 5.0, "Slightly Stoopid": 4.0, "The Strokes": 5.0},
         "Veronica": {"Blues Traveler": 3.0, "Norah Jones": 5.0, "Phoenix": 4.0, "Slightly Stoopid": 2.5, "The Strokes": 3.0}
        }
	 
	 * */
	
	private static void pack() {
		JsonObject json = new JsonParser().parse("{'a':11,'b':22}").getAsJsonObject();
		for(Entry<String, JsonElement> o : json.entrySet()){
			System.out.println(o);
			
			System.out.println(o.getValue().getAsDouble());
		}
	}
//	public static void main(String[] args) {
//		pack();
//	}
}
