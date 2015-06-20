package daemondev.xpense.com.quizapp;

/**
 * Created by Venkatesh on 13/06/2015.
 */
public class TrueFalse {

    private int mQuestionNumber;
    private boolean mTrueQuestions;


    public int getQuestionNumber() {
        return mQuestionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        mQuestionNumber = questionNumber;
    }

    public boolean isTrueQuestions() {
        return mTrueQuestions;
    }

    public void setTrueQuestions(boolean trueQuestions) {
        mTrueQuestions = trueQuestions;
    }

    public TrueFalse(int qNo, boolean trueQ){
        mQuestionNumber=qNo;
        mTrueQuestions=trueQ;
    }
}
