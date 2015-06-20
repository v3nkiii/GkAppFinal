package daemondev.xpense.com.quizapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Button mTrue;
    private Button mFalse;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;
    private Button mCheatButton;

    private boolean mIsCheated;



    @Override
    protected void onActivityResult(int RequestCode , int resultCode , Intent data){
        if(data == null){
            return;
        }
        mIsCheated = data.getBooleanExtra(CheatActivity.EXTRA_VALUE,true);
    }

    private TrueFalse[] mQuestionBank= new TrueFalse[]{new TrueFalse(R.string.question_oceans,true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true)};

    private int mCurrentIndex = 0;

    private void updateQ(){
        int question = mQuestionBank[mCurrentIndex].getQuestionNumber();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestions();
        int messageResId = 0;
        if (mIsCheated) {
            messageResId = R.string.judgement;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_ans;
            } else {
                messageResId = R.string.wrong_ans;
            }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView)findViewById(R.id.question_text);


        mNextButton=(Button)findViewById(R.id.next_question_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheated = false;
                updateQ();
            }
        });

        mPrevButton=(Button) findViewById(R.id.prev_question_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int messageResID1 = 0;
                if (mCurrentIndex == 0) {
                    messageResID1 = R.string.first_q;
                    Toast.makeText(v.getContext(), messageResID1, Toast.LENGTH_SHORT).show();
                } else {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    mIsCheated = false;
                    updateQ();
                }
            }
        });

        mTrue=(Button) findViewById(R.id.true_button);
        mTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalse=(Button) findViewById(R.id.false_button);
        mFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });


        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestions();
                i.putExtra(CheatActivity.EXTRA_VALUE,answerIsTrue);
                startActivityForResult(i, 0);
            }
        });
        updateQ();
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
