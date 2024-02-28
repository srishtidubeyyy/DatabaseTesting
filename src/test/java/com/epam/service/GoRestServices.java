package com.epam.service;

import com.epam.models.PostsRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;

public class GoRestServices {
    public Response getResponseForPosts(String basePath) {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RequestSpecification gettingNumberOfResourceRequest = given();
        return gettingNumberOfResourceRequest.request(Method.GET, basePath);
    }

    public int gettingNumberOfResources(String basePath) {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RequestSpecification gettingNumberOfPostsResponsesRequest = given();
        JSONArray numberOfPostsResources = new JSONArray(gettingNumberOfPostsResponsesRequest.request(Method.GET, basePath).asString());
        return numberOfPostsResources.length();
    }

    public List<PostsRecord> getPostsList(String basePath) throws JsonProcessingException {
        Response response = getResponseForPosts(basePath);
        List<PostsRecord> postsList = response.jsonPath().getList("", PostsRecord.class);
        return postsList;
    }

    public List<PostsRecord> getPostsAtOddPositions(String basePath) throws JsonProcessingException {
        List<PostsRecord> postsList = getPostsList(basePath);

        List<PostsRecord> oddPositionRecords = IntStream.range(0, postsList.size())
                .filter(index -> index % 2 != 1)
                .mapToObj(postsList::get)
                .collect(Collectors.toList());
        return oddPositionRecords;
    }

}

