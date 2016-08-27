package cn.zeffect.view.progressbuttondemco;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.zeffect.view.progressbutton.ProgressButton;

public class MainActivity extends AppCompatActivity {
    private ProgressButton mProgressBtn;
    private Button mFillBtn;
    private Button mStrokeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mProgressBtn = (ProgressButton) findViewById(R.id.progress_btn);
        mProgressBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mProgressBtn.setClickable(false);
                new ProgressTask().execute();
            }
        });
        mFillBtn = (Button) findViewById(R.id.type_fill_btn);
        mFillBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mProgressBtn.setType(ProgressButton.TYPE_FILL);    // the fill type
            }
        });
        mStrokeBtn = (Button) findViewById(R.id.type_stroke_btn);
        mStrokeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mProgressBtn.setType(ProgressButton.TYPE_STROKE);    //the stroke type
            }
        });
    }


    public class ProgressTask extends AsyncTask<Void, Integer, Void> {
        private int mProgress = 0;

        @Override
        protected Void doInBackground(Void... params) {
            while (mProgress >= 0 && mProgress <= 100) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(mProgress += 3);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressBtn.setClickable(true);
            Toast.makeText(MainActivity.this, "finish download task",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBtn.updateProgress(values[0]);
        }

    }
}
