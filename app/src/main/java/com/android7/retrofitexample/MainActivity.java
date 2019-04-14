package com.android7.retrofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    APIInterface apiInterface;
    GraphView mGraph;

    Button mReloadButton;
    EditText mAedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGraph =  findViewById(R.id.graph);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mReloadButton = findViewById(R.id.button);
        mAedit = findViewById(R.id.editTextA);

        mReloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float A = Float.parseFloat(mAedit.getText().toString());

                Call<List<Data>> dataSquareFunction = apiInterface.getSquareFunction(A,0,1);

                dataSquareFunction.enqueue(new Callback<List<Data>>() {
                    @Override
                    public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                        List<Data> dataList = response.body();

                        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

                        double x = 0;

                        for(Data data : dataList){
                            series.appendData(new DataPoint(x,data.number),true,100,true);
                            x+=1;
                        }

                        mGraph.addSeries(series );
                    }

                    @Override
                    public void onFailure(Call<List<Data>> call, Throwable t) {

                    }
                });
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
