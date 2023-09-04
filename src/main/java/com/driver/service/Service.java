package com.driver.service;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    Repository repository=new Repository();
    public String addHotel(Hotel hotel) {
        if(hotel==null||hotel.getHotelName()==null)return "FAILURE";
        return repository.addHotel(hotel);
    }

    public Integer addUser(User user) {
        return repository.addUser(user);
    }

    public String getgetHotelWithMostFacilities() {
        return repository.getgetHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        UUID uid=UUID.randomUUID();
        booking.setBookingId(String.valueOf(uid));
        return repository.bookARoom(booking);
    }

    public int getBookings(Integer aadharCard) {
        return repository.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return repository.updateFacilities(newFacilities,hotelName);
    }
}
