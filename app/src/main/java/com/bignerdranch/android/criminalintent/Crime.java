package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;
import android.text.format.DateFormat;


public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mRequiresPolice;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

   public Date getDate() {
       return mDate;
   }

    public boolean isPoliceRequired() {
        return mRequiresPolice;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setRequiresPolice(boolean mRequiresPolice) {
        this.mRequiresPolice = mRequiresPolice;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

}
