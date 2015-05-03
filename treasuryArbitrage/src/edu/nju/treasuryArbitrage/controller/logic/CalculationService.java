package edu.nju.treasuryArbitrage.controller.logic;

import edu.nju.treasuryArbitrage.model.calculation.Lambda;
import edu.nju.treasuryArbitrage.model.calculation.OptimalKT;
import edu.nju.treasuryArbitrage.model.calculation.Xyz;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalculationService {
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd HH:mm:ss");

    private NetHelper netHelper;

    public CalculationService(NetHelper netHelper) {
        this.netHelper = netHelper;
    }

    public Xyz getTodayXyz(int group) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("group", String.valueOf(group));

        netHelper.setInitPara("modelFxy", parameters);
        JSONArray jsonArray = netHelper.getJSONArrayByGet();
        if (jsonArray == null || jsonArray.length() < 1) {
            return null;
        }

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        Date time = parseDate(jsonObject.getString("time"));
        double x = jsonObject.getDouble("x");
        double y = jsonObject.getDouble("y");
        double z = jsonObject.getDouble("k");
        return new Xyz(group, time, x, y, z);
    }

    public Lambda getTodayLambda(int group) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("group", String.valueOf(group));

        netHelper.setInitPara("modelWxy", parameters);
        JSONArray jsonArray = netHelper.getJSONArrayByGet();
        if (jsonArray == null || jsonArray.length() < 1) {
            return null;
        }

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        Date time = parseDate(jsonObject.getString("time"));
        double lambda1 = jsonObject.getDouble("lambda1");
        double lambda2 = jsonObject.getDouble("lambda2");
        return new Lambda(group, time, lambda1, lambda2);
    }

    public OptimalKT getTodayOptimalKT(int group) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("group", String.valueOf(group));

        netHelper.setInitPara("modelDj", parameters);
        JSONArray jsonArray = netHelper.getJSONArrayByGet();
        if (jsonArray == null || jsonArray.length() < 1) {
            return null;
        }

        JSONObject jsonObject = jsonArray.getJSONObject(0);
        Date time = parseDate(jsonObject.getString("time"));
        double k = jsonObject.getDouble("k");
        double t = jsonObject.getDouble("t");
        return new OptimalKT(group, time, k, t);
    }

    private Date parseDate(String dateString) {
        Date time = null;
        try {
            time = DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            time = new Date();
            e.printStackTrace();
        }
        return time;
    }
}
