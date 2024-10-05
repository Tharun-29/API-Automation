package files;

public class RestfulBooker {
    public static String CreateAuthPayload() {
    	return "{\r\n"
    			+ "    \"username\" : \"admin\",\r\n"
    			+ "    \"password\" : \"password123\"\r\n"
    			+ "}";
    }
    
    public static String CreateBookingPayload() {
    	return "{\r\n"
    			+ "    \"firstname\" : \"Jim\",\r\n"
    			+ "    \"lastname\" : \"Brown\",\r\n"
    			+ "    \"totalprice\" : 111,\r\n"
    			+ "    \"depositpaid\" : true,\r\n"
    			+ "    \"bookingdates\" : {\r\n"
    			+ "        \"checkin\" : \"2018-01-01\",\r\n"
    			+ "        \"checkout\" : \"2019-01-01\"\r\n"
    			+ "    },\r\n"
    			+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
    			+ "}";
    }
    
    
}
