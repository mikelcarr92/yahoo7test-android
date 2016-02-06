package com.example.yahooseventest;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**********************************
 * Created by Michael Carr on 06-Feb-16.
 *********************************/
public class MainActivity extends AppCompatActivity {

    private int mStart = 0;
    private int mCount = 1;
    private ArrayList<Program> mListItems = new ArrayList<>();
    private ResultsListAdapter mListAdapter;
    private boolean mIsLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        mListAdapter = new ResultsListAdapter(MainActivity.this, mListItems);
        listView.setAdapter(mListAdapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount >= totalItemCount - 1 && !mIsLoading && mStart < mCount) {
                    mIsLoading = true;
                    getResults();
                }
            }
        });
    }

    private void getResults() {

        final RequestQueue queue = Volley.newRequestQueue(this);

        GsonRequest<Result> gsonRequest = new GsonRequest<>(getURL(mStart), Result.class, null,
                new Response.Listener<Result>() {
                    @Override
                    public void onResponse(Result response) {

                        if (response.results != null) {
                            mListItems.addAll(response.results);
                            mListAdapter.notifyDataSetChanged();

                            mCount = response.count;
                            mStart++;
                            mIsLoading = false;
                        }
                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mIsLoading = false;

                        //Occasionally server returns 503, not sure if intentional
                        if (error != null && error.networkResponse != null && error.networkResponse.statusCode == 503) {
                            getResults();
                        }
                    }
                }
        );

        queue.add(gsonRequest);
    }

    private String getURL(int start) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.whatsbeef.net")
                .appendPath("wabz")
                .appendPath("guide.php")
                .appendQueryParameter("start", Integer.toString(start));
        return builder.build().toString();
    }
}
