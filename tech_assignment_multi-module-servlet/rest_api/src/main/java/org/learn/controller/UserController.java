package org.learn.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.learn.adapter.LocalDateTypeAdapter;
import org.learn.dao.UserDao;
import org.learn.dao.UserDaoImpl;
import org.learn.dto.ErrorResponse;
import org.learn.dto.UserDto;
import org.learn.dto.UserResponse;
import org.learn.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Path("user")
@Log4j2
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private UserDao userDao;
    private Gson gson;
    private ModelMapper mapper;

    public UserController(){
        this.userDao = new UserDaoImpl();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUser(String userStr) {
        UserDto dto;
        try {
            dto = gson.fromJson(userStr, UserDto.class);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse("400","invalid json, "+e.getMessage());
           return Response.status(400).entity(errorResponse).build();
        }
        //valid dto and getting user
        User user =  mapper.map(dto,User.class);
        log.info("user info : {}",user);
        try {
            userDao.save(user);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse("400","cannot insert data, "+e.getMessage());
            return Response.status(400).entity(errorResponse).build();
        }
        return  Response.status(200).entity(dto).build();
    }

    @GET
    @Path("{userId}")
    public Response getUser(@PathParam("userId") Integer userId) {
        User user = userDao.get(userId);
        if(user == null){
            ErrorResponse errorResponse = new ErrorResponse("404","cannot find the user with userid, "+userId);
            return Response.status(404).entity(errorResponse).build();
        }
        log.info("user info, {}",user);
        return Response.ok(mapper.map(user,UserDto.class)).build() ;
    }

    @GET
    public Response getUsers(){
        List<User> users = userDao.getAll();
        List<UserDto> userDtoList = users.stream().map(user -> mapper.map(user,UserDto.class)).collect(Collectors.toList());
        return Response.ok(new UserResponse(userDtoList)).build() ;
    }
}
