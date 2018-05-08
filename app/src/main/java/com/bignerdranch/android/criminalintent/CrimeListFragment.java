package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class CrimeHolder extends ViewHolder implements View.OnClickListener{

        private Crime mCrime;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        //private Button mPoliceButton;
        private ImageView mSolvedImageView;
        //private TextView mTimeTextView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            //super(inflater.inflate(R.layout.list_item_serious_crime, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
            //mTimeTextView = (TextView) itemView.findViewById();
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            String df = (String) DateFormat.format("EEE, MMM dd, yyyy", mCrime.getDate());
            mDateTextView.setText(df);
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
            //mTimeTextView.setText();
        }

        @Override
        public void onClick(View view) {

            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }

    }

   /* private class CrimeHolder1 extends ViewHolder implements View.OnClickListener{

        private Crime mCrime;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Button mPoliceButton;
        private ImageView mSolvedImageView;

        public CrimeHolder1(LayoutInflater inflater, ViewGroup parent) {
            //super(inflater.inflate(R.layout.list_item_crime, parent, false));
            super(inflater.inflate(R.layout.list_item_serious_crime, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
            mPoliceButton = (Button) itemView.findViewById(R.id.contact_police_button);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            String df = (String) DateFormat.format("EEE, MMM dd, yyyy", mCrime.getDate());
            mDateTextView.setText(df);
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {

            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }

    }*/

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
            /*switch (viewType) {
                case 0: return new CrimeHolder(layoutInflater, parent);
                case 1: return new CrimeHolder1(layoutInflater, parent);
            }*/
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
            /*switch (holder.getItemViewType()) {
                case 0:
                    CrimeHolder crimeHolder = (CrimeHolder) holder;
                    crimeHolder.bind(crime);
                case 1:
                    CrimeHolder1 crimeHolder1 = (CrimeHolder1) holder;
                    crimeHolder1.bind(crime);
            }*/
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

       /*@Override
        public int getItemViewType(int position) {
            if (mCrimes.get(position).getRequiresPolice()) {
                return 1;
            } else {
                return 0;
            }
        }*/
    }
}
