package com.tabasumgroupofcompanies.quiz;

public class TrueFalse {
    private int mQuestionId;
    private boolean mAnswer;
    public TrueFalse(int providedQuesId,boolean providedAns){
        mQuestionId=providedQuesId;
        mAnswer=providedAns;
    }
    public int getQuestionId() {
        return mQuestionId;
    }
    public boolean isAnswer() {
        return mAnswer;
    }
}
