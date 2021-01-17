package br.com.soulit.starwars.ws.response;

import java.io.Serializable;

public class Status implements Serializable {
	
	private static final long serialVersionUID = -2850508561810701996L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
