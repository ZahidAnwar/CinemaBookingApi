CinemaBookingAPI

I have used Spring Web MVC 4 and Hibernate (5.2.9) to create the web api project. The main three controllers are as follows.

ApiController.java

MovieController.java

BookingController.java

ApiController:

Responsible for api authentication.

The controller can also be use to save invalid access details (Not implemented yet). 

A token is required to access the API (maximum character length is 100). 

MovieController

To retrieve a list of the films showing at the cinema simply replace the token value, with valid token.

www.example.com/movie/list?token=thisistesttoken 

BookingController

The BookingController.java serve three end points. To book a seat at a showing of a film, simply use ‘/booking/add’ end point. It’s a POST method which required token and Booking object.

‘/booking/cancel’ end point can be used to cancel a previously booked seat. Required parameters are token and bookingId.

To retrieve a list of a user’s future bookings, use ‘/booking/list’ end point. A token is required to retrieve the list. The list should contain all users booking details (users who has reserved and their reserve date is one day ahead from now).


Testing:

ApiControllerTest.java

BookingControllerTest.java

MovieControllerTest.java


Live AWS instance path:

/movie/list

http://ec2-52-56-213-33.eu-west-2.compute.amazonaws.com/movie/list?token=6d17f079-2186-49b9-97f3-7f45125b4486 

/booking/list

http://ec2-52-56-213-33.eu-west-2.compute.amazonaws.com/booking/list?token=6d17f079-2186-49b9-97f3-7f45125b4486 



