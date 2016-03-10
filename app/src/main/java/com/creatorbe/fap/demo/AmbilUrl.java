package com.creatorbe.fap.demo;

import android.os.AsyncTask;

public class AmbilUrl extends AsyncTask<String, Void, String>{

	TampilkanUrl container;
	public AmbilUrl(TampilkanUrl f) {
		this.container = f;
	}
	
	@Override
	protected String doInBackground(String... params) {
		try {
			Thread.sleep(3000);

		}catch(Exception ex) {}
		return "Website ku "+params[0];
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		container.showProgressBar();
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if(container!=null && container.getActivity()!=null) {
			container.populateResult(result);
			container.hideProgressBar();
			this.container = null;
		}
	}
}
