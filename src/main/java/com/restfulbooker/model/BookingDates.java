/*
 * Created By: Hiren Patel
 * Project Name: Restful-Booker-API-Serenity-Week-21
 */

package com.restfulbooker.model;

public class BookingDates {
    public String checkin;
    public String checkout;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public static BookingDates getBookingDates(String checkIn, String checkout) {
        BookingDates bookingDates = new BookingDates();
        if (checkIn != null) {
            bookingDates.setCheckin(checkIn);
        }
        if (checkout != null) {
            bookingDates.setCheckout(checkout);
        }
        return bookingDates;
    }
}
