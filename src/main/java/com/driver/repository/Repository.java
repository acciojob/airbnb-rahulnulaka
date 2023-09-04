package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@org.springframework.stereotype.Repository
public class Repository {
    HashMap<String ,Hotel> hoteldb=new HashMap<>();
    HashMap<Integer,User> userdb=new HashMap<>();
    List<Hotel> listhotel=new ArrayList<>();
    HashMap<Integer,Integer> bookingdb=new HashMap<>();
    public String addHotel(Hotel hotel) {
        String name=hotel.getHotelName();
        if(hoteldb.containsKey(name)){
            return "FAILURE";
        }
        hoteldb.put(name,hotel);
        listhotel.add(hotel);
        return "SUCCESS";
    }

    public Integer addUser(User user) {
        userdb.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }

    public String getgetHotelWithMostFacilities() {
       listhotel.sort((a,b)-> b.getFacilities().size()-a.getFacilities().size());
       Hotel hotel=listhotel.get(0);
       if(hotel.getFacilities().isEmpty())return "";
       return hotel.getHotelName();
    }

    public int bookARoom(Booking booking) {
        Hotel hotel=hoteldb.get(booking.getHotelName());
        if(booking.getNoOfRooms()>hotel.getAvailableRooms()){
            return -1;
        }
        int amountpaid=booking.getNoOfRooms()*hotel.getPricePerNight();
        booking.setAmountToBePaid(amountpaid);
        if(bookingdb.containsKey(booking.getBookingAadharCard())){
            bookingdb.put(booking.getBookingAadharCard(),bookingdb.get(booking.getBookingAadharCard())+1);
        }
        else{
            bookingdb.put(booking.getBookingAadharCard(),1);
        }
        return amountpaid;
    }


    public int getBookings(Integer aadharCard) {
        if(bookingdb.containsKey(aadharCard)){
            return bookingdb.get(aadharCard);
        }
        return 0;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel=hoteldb.get(hotelName);
        List<Facility> list=hotel.getFacilities();
        for(Facility f:newFacilities){
            if(!list.contains(f))list.add(f);
        }
        return hotel;
    }
}
