package com.bookmyshow.webclient.controller;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.bookmyshow.webclient.model.BookRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bookmyshow-client")
public class BookMyShowWebClient {

	WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("http://localhost:8080/bookmyshow/service")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@RequestMapping(value = "/makeABooking", method = RequestMethod.POST)
	public Mono<String> makeABooking(@RequestBody BookRequest bookRequest) {
		return webClient.post().uri("/bookingShow").syncBody(bookRequest).retrieve().bodyToMono(String.class);
	}

	@RequestMapping(value = "/getAllMyBookings", method = RequestMethod.GET)
	public Flux<BookRequest> getAllMyBookings() {
		return webClient.get().uri("/getAllBooking").retrieve().bodyToFlux(BookRequest.class);
	}

	@RequestMapping(value = "/getMyBooking/{bookingId}", method = RequestMethod.GET)
	public Mono<BookRequest> getBookingById(@PathVariable int bookingId) {
		return webClient.get().uri("/getBooking/" + bookingId).retrieve().bodyToMono(BookRequest.class);
	}

	@RequestMapping(value = "/deleteMyBooking/{bookingId}", method = RequestMethod.DELETE)
	public Mono<String> deleteMyBooking(@PathVariable int bookingId) {
		return webClient.delete().uri("/deleteBooking/" + bookingId).retrieve().bodyToMono(String.class);
	}

	@RequestMapping(value = "/updateMyBooking/{bookingId}", method = RequestMethod.PUT)
	public Mono<BookRequest> updateMyBooking(@RequestBody BookRequest bookRequest, @PathVariable int bookingId) {
		return webClient.put().uri("/updateBooking/" + bookingId).bodyValue(bookRequest).retrieve()
				.bodyToMono(BookRequest.class);
	}
}
