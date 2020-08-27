package com.aryanadewi_tugaspraktikum.responsi_17030033_17030035.recyclerview_connect_api.network;

import androidx.appcompat.app.AppCompatActivity;

public class MusicPopularActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private final static String API_KEY ="01d598644f83a99c36c7819529e051ce";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_popular);


        recyclerView= findViewById(R.id.rv_music_popular);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<MusicPopularResponse> call = apiInterface.getMusicPopular(API_KEY);
        call.enqueue(new Callback<MusicPopularActivityPopularResponse>() {
            @Override
            public void onResponse(Call<MusicPopularActivityPopularResponse> call, Response<MusicPopularActivityPopularResponse> response) {
                final List<Result> results =response.body().getResults();
                recyclerView.setAdapter(new MusicPopularActivity()PopularAdapter(results, R.layout.item_music_popular, getApplicationContext()));

                /*perintah klik recyclerview*/
                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                    GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                        public boolean onSingleTapUp(MotionEvent e){
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                        if (child != null && gestureDetector.onTouchEvent(e)){
                            int position = rv.getChildAdapterPosition(child);
                            Toast.makeText(getApplicationContext(), "Id : " + results.get(position).getId() + " selected", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MusicPopularActivityPopularActivity.this, DetailActivity.class);
                            i.putExtra("title", results.get(position).getTitle());
                            i.putExtra("date", results.get(position).getReleaseDate());
                            i.putExtra("vote", results.get(position).getVoteAverage().toString());
                            i.putExtra("overview", results.get(position).getOverview());
                            i.putExtra("bg", results.get(position).getPosterPath());
                            MusicPopularActivityPopularActivity.this.startActivity(i);

                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });

            }

            @Override
            public void onFailure(Call<MusicPopularActivityPopularResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Get Data Music Failed", Toast.LENGTH_LONG).show();
            }
        });


    }



}
