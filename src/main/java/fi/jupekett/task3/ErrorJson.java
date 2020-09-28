package fi.jupekett.task3;

import com.google.gson.Gson;

public class ErrorJson {
	private int status;
	private String error;
	
	public ErrorJson(int status, String error) {
		// TODO verification at least on status
		this.status = status;
		this.error = error;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
