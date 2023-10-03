package vn.edu.usth.newsreaderonline;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecycleViewAdapter extends RecyclerView.Adapter<NewsRecycleViewAdapter.NewsViewHolder> {

    List<Article> articleList;
    NewsRecycleViewAdapter(List<Article> articleList){
        this.articleList = articleList;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_header,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.articleTitle.setText(article.getTitle());
        holder.articleSource.setText(article.getSource().getName());
        Picasso.get().load(article.getUrlToImage())
                .error(R.drawable.no_img)
                .into(holder.imageView);

    }
    //Clear the old article list and add new articles
    void updateArticleData(List<Article> articleData){
        articleList.clear();
        articleList.addAll(articleData);

    }
    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView articleTitle, articleSource;
        ImageView imageView;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            articleTitle = itemView.findViewById(R.id.titleViewId);
            articleSource = itemView.findViewById(R.id.sourceViewId);
            imageView = itemView.findViewById(R.id.imageViewId);
        }
    }
}
