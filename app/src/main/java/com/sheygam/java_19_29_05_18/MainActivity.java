package com.sheygam.java_19_29_05_18;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultTxt;
    private Button changeResBtn;
    private MyTask myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MY_TAG", "onCreate: ");
        setContentView(R.layout.activity_main);
        resultTxt = findViewById(R.id.resultTxt);
        changeResBtn = findViewById(R.id.changeButton);
        changeResBtn.setOnClickListener(this);
//        if(savedInstanceState != null){
//            resultTxt.setText(savedInstanceState.getString("RESULT"));
//        }else{
//            Toast.makeText(this, "Bundle = null", Toast.LENGTH_SHORT).show();
//        }

        myTask = (MyTask) getLastCustomNonConfigurationInstance();
        if (myTask!=null){
            myTask.attachActivity(this);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.changeButton){
//            resultTxt.setText("New text");
           myTask = new MyTask();
           myTask.attachActivity(this);
           myTask.execute();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("RESULT",resultTxt.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("MY_TAG", "onRestoreInstanceState: ");
        resultTxt.setText(savedInstanceState.getString("RESULT"));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return myTask;
    }



    @Override
    protected void onDestroy() {
        Log.d("MY_TAG", "onDestroy: ");
        if(!isFinishing()){
            if(myTask!=null){
                myTask.detachActivity();
            }
        }
        super.onDestroy();
    }


    class MyTask extends AsyncTask<Void,String,String>{
        private MainActivity activity;

        public void attachActivity(MainActivity activity){
            this.activity = activity;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            if(activity!=null) {
                activity.resultTxt.setText(values[0]);
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            for (int i = 0; i<100;i++){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(String.valueOf(i));
                Log.d("MY_TAG", "doInBackground: " + i);
            }
            return "Finish";
        }

        @Override
        protected void onPostExecute(String s) {
            if (activity!=null) {
                activity.resultTxt.setText(s);
            }
        }

        public void detachActivity(){
            activity = null;
        }
    }
}
