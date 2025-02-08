package java_sql_project.domain;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class reservation {
	public String id;
	public String consumer_id;
	public String restaurant_id;
	public Date reservation_date;
	public Time reservation_time;
	public int number_of_people;
	public ReservationStatus status;
	public Timestamp created_at;
	public enum ReservationStatus {
	    confirmed, cancelled
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConsumer_id() {
		return consumer_id;
	}
	public void setConsumer_id(String consumer_id) {
		this.consumer_id = consumer_id;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public Date getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}
	public Time getReservation_time() {
		return reservation_time;
	}
	public void setReservation_time(Time reservation_time) {
		this.reservation_time = reservation_time;
	}
	public int getNumber_of_people() {
		return number_of_people;
	}
	public void setNumber_of_people(int number_of_people) {
		this.number_of_people = number_of_people;
	}
	public ReservationStatus getStatus() {
		return status;
	}
	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	
}
