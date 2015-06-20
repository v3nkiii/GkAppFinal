package daemondev.xpense.com.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import daemondev.xpense.com.quizapp.R;

/**
 * Created by Venkatesh on 17/06/2015.
 */
public class CheatActivity extends Activity {

    private boolean isAnsTrue;
    public static final String EXTRA_VALUE = "daemondev.xpense.com.quizapp.answer_is_true";
    public static final String ANS_SHOWN = "daemondev.xpense.com.quizapp.answer_shown";

    private TextView mConfirmTextView;
    private Button mConfirmButton;

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(ANS_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setAnswerShownResult(false);
        isAnsTrue = getIntent().getBooleanExtra(EXTRA_VALUE,true);

        mConfirmTextView = (TextView) findViewById(R.id.answerTextView);

        mConfirmButton = (Button) findViewById(R.id.showAnswerButton);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAnsTrue){
                    mConfirmTextView.setText(R.string.true_button);
                }
                else{
                    mConfirmTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });

    }
}
