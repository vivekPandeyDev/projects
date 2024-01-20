package org.learn.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.learn.dao.GeoDao;
import org.learn.dto.CityList;
import org.learn.dto.CountryList;
import org.learn.dto.StateList;

import javax.json.JsonArray;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("geo")
@Produces(MediaType.APPLICATION_JSON)
@Log4j2
public class GeoController {

    private GeoDao geoDao = new GeoDao();
    @GET
    @Path("/countries")
    public CountryList getCountry(){
        List<String> countryList = geoDao.getCountryToStates().keySet().stream().collect(Collectors.toList());
        return  new CountryList(countryList);
    }

    @GET
    @Path("/countries/{state}")
    public StateList getState(@PathParam("state") String state){
        List<String> stateList = geoDao.getCountryToStates().get(state);
        return new StateList(stateList);
    }

    @GET
    @Path("/states/{city}")
    public CityList getCity(@PathParam("city") String city){
        List<String> cityList = geoDao.getStateToCities().get(city);
        return new CityList(cityList);
    }
}
