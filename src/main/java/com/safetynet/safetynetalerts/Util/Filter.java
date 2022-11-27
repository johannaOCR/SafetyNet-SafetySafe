package com.safetynet.safetynetalerts.Util;

import com.google.gson.Gson;


import java.util.HashMap;
import java.util.List;

public class Filter {

    public String listStringToJson(List<String> list){
        HashMap<String, List<String>> result = new HashMap<>();
        result.put("phone", list);
        return new Gson().toJson(result);
    }



}
