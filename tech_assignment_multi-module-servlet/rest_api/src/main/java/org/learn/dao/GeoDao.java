package org.learn.dao;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public class GeoDao {
    private Map<String, List<String>> countryToStates  = new HashMap<>();
    private Map<String,List<String>> stateToCities  = new HashMap<>();

    public GeoDao(){
        setCountry();
        setState();
    }

    public void setState(){
        stateToCities.put("delhi", Stream.of("mustafabad", "Karawal Nagar").collect(Collectors.toList()));
        stateToCities.put("haryana",Stream.of("faridabad","gurugram").collect(Collectors.toList()));
        stateToCities.put("lahore",Stream.of("islamabad","balochistan").collect(Collectors.toList()));
        stateToCities.put("peshawar",Stream.of("gilgit-baltistan").collect(Collectors.toList()));
    }

    public void setCountry(){
        countryToStates.put("india",Stream.of("delhi","haryana").collect(Collectors.toList()));
        countryToStates.put("pakistan",Stream.of("lahore","peshawar").collect(Collectors.toList()));
    }

}
