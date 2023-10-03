package vn.edu.usth.newsreaderonline;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.google.android.material.tabs.TabLayout;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class NewsReaderOnlineActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Article> articleList = new ArrayList<>();
    NewsRecycleViewAdapter NewsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_reader_online);
        recyclerView = findViewById(R.id.recycleViewId);

        //Match the tab with the appropriate tabLayout
        TabLayout tabLayout = findViewById(R.id.TabLayoutsMenu);

        //Set addOnTabSelectedListener to tabLayout: each tab item opens corresponding category
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                CharSequence tabText = tab.getText();
                String tabTextString = tabText !=null ? tabText.toString() :"";//Check if tab Item text is null, if null return ""
                getNewsArticle(tabTextString);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //initialize RecycleView to display articles
        setupRecycleView();

        //Retrieve articles using API key
        getNewsArticle("GENERAL");
    }
    void setupRecycleView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewsAdapter = new NewsRecycleViewAdapter(articleList);
        recyclerView.setAdapter(NewsAdapter);

    }
    void getNewsArticle(String NewsCategory){
        NewsApiClient newsApiClient = new NewsApiClient("1ca640b82f0341e6b4c055fe38ac77b2");
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .language("en")
                        .category(NewsCategory)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        runOnUiThread(()->{
                            articleList = response.getArticles();
                            NewsAdapter.updateArticleData(articleList);
                            NewsAdapter.notifyDataSetChanged();
                        });
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.i("No Response from the server",throwable.getMessage());
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }
}
