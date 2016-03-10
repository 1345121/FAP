package com.creatorbe.fap.demo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TampilkanUrl extends Fragment {

	View mMainView;
	String mResult;
	AmbilUrl mTask;
	private ProgressDialog pDialog;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mMainView = inflater.inflate(R.layout.url_display,container, false);
		
		Button btnFetch = (Button)mMainView.findViewById(R.id.btnFetch);
		btnFetch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String urlS = "http://creatorbe.com";
				startURLFetch(urlS);
			}
		});

		setRetainInstance(true);
		pDialog = new ProgressDialog(getActivity());
		pDialog.setMessage("Loading Bro ...");
		pDialog.setIndeterminate(false);
		return mMainView;
	}

	protected void startURLFetch(String urlS) {
		mTask = new AmbilUrl(this);
		mTask.execute(urlS);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		if(isTaskRunning(mTask)) {
			showProgressBar();
		}else {
			hideProgressBar();
		}
		if(mResult!=null) {
			populateResult(mResult);
		}
		super.onActivityCreated(savedInstanceState);
	}
	
	public void showProgressBar() {
		TextView resultView = (TextView)getActivity().findViewById(R.id.textUrlContent);
		resultView.setVisibility(View.GONE);
		if (!pDialog.isShowing())
			pDialog.show();
	}
	
	public void hideProgressBar() {
		TextView resultView = (TextView)getActivity().findViewById(R.id.textUrlContent);
		resultView.setVisibility(View.VISIBLE);
		if (pDialog.isShowing())
			pDialog.dismiss();

	}
	
	public void populateResult(String s) {
		TextView resultView = (TextView)getActivity().findViewById(R.id.textUrlContent);
		resultView.setText(s);
	}
	
	protected boolean isTaskRunning(AmbilUrl task) {
		if(task==null ) {
			return false;
		} else if(task.getStatus() == AmbilUrl.Status.FINISHED){
			return false;
		} else {
			return true;
		}
	}
	
}
