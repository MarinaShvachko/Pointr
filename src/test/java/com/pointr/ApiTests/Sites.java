package com.pointr.ApiTests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import static com.jayway.restassured.RestAssured.given;

@Getter
@Setter
public class Sites {
    private String id;
    private String title;

    private Buildings[] buildingsOnTheSite;

    public void addSiteWithoutBuildings(String id, String siteName) {
        Sites site = new Sites();
        site.setId(id);
        site.setTitle(siteName);
        given().when().contentType(ContentType.JSON).body(site).post("http://localhost:3000/Sites");
    }

    public void addSiteWithBuildings() {
        Buildings building1 = new Buildings();
        building1.setId("77");
        building1.setName("School");
        building1.setPhone("678");
        building1.setAddress("School str. 1");

        Buildings building2 = new Buildings();
        building2.setId("78");
        building2.setName("House");
        building2.setPhone("111");
        building2.setAddress("School str. 2");

        Sites sites = new Sites();
        sites.setId("87");
        sites.setTitle("Living space");
        sites.setBuildingsOnTheSite(new Buildings[]{building1, building2});

        given().when().contentType(ContentType.JSON).body(sites).post("http://localhost:3000/Sites");
    }

    public void getSiteById(String siteId) {
        Response resp = given().when().get("http://localhost:3000/Sites/"+ siteId);
        System.out.println(resp.asString());
    }

    public void changeSite() {
        given().body("{\"title\":\"living district\"}").when().contentType(ContentType.JSON).patch("http://localhost:3000/Sites/87");
    }

    public void deleteSiteById(String id) {
        given().when().delete("http://localhost:3000/Sites/"+ id);
    }
}
