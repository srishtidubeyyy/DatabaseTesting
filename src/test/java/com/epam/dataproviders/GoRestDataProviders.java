package com.epam.dataproviders;

import com.epam.models.PostsRecord;
import org.testng.annotations.DataProvider;

public class GoRestDataProviders {
    @DataProvider(name = "goRestPostsDataProvider")
    public Object[][] postsStatuscodeData() {
        return new Object[][]{
                {200},
                {201}
        };
    }

    @DataProvider(name = "goRestNumberOfPostsDataProvider")
    public Object[][] numberOfPostsData() {
        return new Object[][]{
                {10}
        };
    }

    @DataProvider(name = "postsData")
    public static Object[][] postsData() {
        return new Object[][]{
                {"102816", "Aperio debeo vesica fuga sustineo denuo crepusculum terra desparatus."},
                {"102814", "Calamitas subnecto surgo thermae studio."},
                {"102811", "Vultuosus acerbitas aut tui accusator viridis quo vulnero."},
                {"102808", "Compono supellex fugiat tenetur appono tibi vomito."},
                {"102804", "Suscipio tempus vel uredo sub celo blandior tamisium odit."}
        };
    }


}
