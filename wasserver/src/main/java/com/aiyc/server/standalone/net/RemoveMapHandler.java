 
package com.aiyc.server.standalone.net;


import com.aiyc.server.standalone.core.Map;
import com.aiyc.server.standalone.db.HomeFactory;
import com.aiyc.server.standalone.db.homes.MapHome;
import com.aiyc.server.standalone.json.GsonFactory;
import com.aiyc.server.standalone.net.Response.Status;
import com.aiyc.server.standalone.util.Log;
import com.google.gson.JsonElement;

public class RemoveMapHandler implements IHandler {

	
	MapHome mapHome;
	
	public RemoveMapHandler() {
		mapHome = HomeFactory.getMapHome();
	}
	
	/**
	 * @see IHandler#handle(JsonElement)
	 */
 
	public Response handle(JsonElement data) {
		
		Response res;
		
		Map map = GsonFactory.getGsonInstance().fromJson(data, Map.class);
		if(mapHome.remove(map)) {
			res = new Response(Status.ok, null, null);
			Log.getLogger().finer("removed map from database");
		} else {
			res = new Response(Status.failed, "could not remove from database", map);
			Log.getLogger().fine("could not remove map from database");
		}
		
		
		
		return res;
	}

}
