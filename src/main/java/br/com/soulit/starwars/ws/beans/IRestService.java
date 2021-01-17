package br.com.soulit.starwars.ws.beans;

import java.io.Serializable;

import javax.ws.rs.core.Response;

public interface IRestService<IN,OUT> extends Serializable{

	public Response getResponse(IN in);
	
}
