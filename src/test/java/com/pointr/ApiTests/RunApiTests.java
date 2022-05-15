package com.pointr.ApiTests;


import org.testng.annotations.Test;

//TODO combine all test by logic, add Assertions, add CI, add static variable id for buildings and sites and constructor to increase it
public class RunApiTests {
    Buildings buildings = new Buildings();
    Sites sites = new Sites();

    @Test
    public void addBuilding() {
        Buildings buildings = new Buildings();
        buildings.addOneBuilding("2", "test name", "test phone", "test address");
    }

    @Test
    public void getBuildingById() {
        buildings.getBuildingById("1");
    }

    @Test
    public void changeBuilding(String idOfBuildingToChange) {
        buildings.changeBuilding("2");
    }

    @Test
    public void deleteBuildingById() {
        buildings.deleteBuildingById("2");
    }

    @Test
    public void addSiteWithoutBuildings() {
        sites.addSiteWithoutBuildings("5", "trtrtr");
    }

    @Test
    public void addSiteWithBuildings() {
        sites.addSiteWithBuildings();
    }

    @Test
    public void changeSiteById() {
        sites.changeSite();
    }

    @Test
    public void getSiteById() {
        sites.getSiteById("87");
    }

    @Test
    public void deleteSiteById() {
        sites.deleteSiteById("87");
    }
}
