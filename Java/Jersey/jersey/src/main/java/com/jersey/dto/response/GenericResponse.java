package com.jersey.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jersey.util.common.GsonProvider;

@JsonInclude(Include.NON_NULL)
public class GenericResponse implements Serializable {

	private static final long serialVersionUID = 203024160428521170L;

	private int success;
	private String message;
	private String error;
	private String description;
	private Object data;

	public GenericResponse() {
		super();
	}

	private GenericResponse(Builder builder) {
		this.success = builder.success;
		this.message = builder.message;
		this.error = builder.error;
		this.description = builder.description;
		this.data = builder.data;
	}

	public int getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getError() {
		return error;
	}

	public String getDescription() {
		return description;
	}

	public Object getData() {
		return data;
	}

	public static class Builder {

		private int success;
		private String message;
		private String error;
		private String description;
		private Object data;

		public Builder() {
		}

		public Builder success() {
			this.success = 1;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder error(String error) {
			this.error = error;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder data(Object data) {
			this.data = data;
			return this;
		}

		public GenericResponse build() {
			return new GenericResponse(this);
		}
	}

	@Override
	public String toString() {
		return GsonProvider.gson.toJson(this);
	}

}
