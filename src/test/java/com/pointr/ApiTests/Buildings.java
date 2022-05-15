package com.pointr.ApiTests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import static com.jayway.restassured.RestAssured.given;

@Getter
@Setter
public class Buildings {
    private String id;
    private String name;
    private String phone;
    private String address;

//TODO revise how exclude a variable for not having setter automatically and add url how a variable in a class
    public void addOneBuilding(String id, String name, String phone, String address) {
        Buildings building = new Buildings();
        building.setId(id);
        building.setName(name);
        building.setPhone(phone);
        building.setAddress(address);
        given().when().contentType(ContentType.JSON).body(building).post("http://localhost:3000/Buildings");
    }

    public void getBuildingById(String id) {
        Response response = given().when().get("http://localhost:3000/Buildings/" + id);
        System.out.println(response.asString());
    }

    //TODO revise how use concatenation in json and add variables in method
    public void changeBuilding(String idOfBuildingToChange) {
        given().body("{\"name\" : \"new new value 2\"}").
                when().
                contentType(ContentType.JSON).
                patch("http://localhost:3000/Buildings/" + idOfBuildingToChange);
    }

    public void deleteBuildingById(String id) {
        given().when().delete("http://localhost:3000/Buildings/" + id);
    }
}
