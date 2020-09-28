package com.bookmyshow.webclient.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

	public int bookingId;
	private String userName;
	private String showName;
	@JsonFormat(pattern = "MM/dd/yyyy", shape = JsonFormat.Shape.STRING, timezone = "USA/Chicago")
	private Date bookingTime;
	private int userCount;
	private Double price;

}
