package com.epam.tests;

import com.epam.dataproviders.GoRestDataProviders;
import com.epam.models.PostsRecord;
import com.epam.service.GoRestServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.port;


public class goRestTests {
    GoRestServices goRestServices;

    @BeforeClass
    public void setUp() {
        port = port;
    }

    @BeforeTest
    public void setPost() {
        goRestServices = new GoRestServices();
    }

    @Test(dataProviderClass = GoRestDataProviders.class, dataProvider = "goRestPostsDataProvider")
    public void validatingNumberOfResourcesInPosts(int expectedNumberOfResources) {
        Assert.assertEquals(goRestServices.getResponseForPosts("/posts").getStatusCode(), expectedNumberOfResources);
    }

    @Test(dataProviderClass = GoRestDataProviders.class, dataProvider = "goRestNumberOfPostsDataProvider")
    public void verifyingNumberOfPostsResponses(int expectedNumberOfPostsResponses) {
        Assert.assertEquals(goRestServices.gettingNumberOfResources("/posts"), expectedNumberOfPostsResponses);
    }

    @Test(dataProvider = "postsData", dataProviderClass = GoRestDataProviders.class)
    public void validatingOddResponsesBody(String expectedId, String expectedTitle) throws JsonProcessingException {
        List<PostsRecord> postsAtOddPositionList = goRestServices.getPostsAtOddPositions("/posts");
        Optional<PostsRecord> matchingRecord = postsAtOddPositionList.stream()
                .filter(record -> String.valueOf(record.id()).equals(expectedId))
                .findFirst();
        Assert.assertEquals(String.valueOf(matchingRecord.get().id()), expectedId);
        Assert.assertEquals(matchingRecord.get().title(), expectedTitle);
    }

    @AfterTest
    public void cleanUpPostService() {
        goRestServices = null;
    }
}
