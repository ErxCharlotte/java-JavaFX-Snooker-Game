package PoolGame.Config;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class PocketsConfig implements Configurable {
    private List<PocketConfig> pockets;

    public PocketsConfig(Object obj) {
        this.parseJSON(obj);
    }

    public PocketsConfig(List<PocketConfig> pocketsList) {
        this.init(pocketsList);
    }

    private void init(List<PocketConfig> pocketsList) {
        this.pockets = pocketsList;
    }

    public Configurable parseJSON(Object obj) {
        List<PocketConfig> ls = new ArrayList<>();
        JSONArray json = (JSONArray) obj;

        for (Object pocket : json) {
            ls.add(new PocketConfig(pocket));
        }
        this.init(ls);
        return this;
    }

    public List<PocketConfig> getPocketConfigs() {
        return this.pockets;
    }
}
