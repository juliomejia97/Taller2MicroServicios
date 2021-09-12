package co.javeriana.ws.rest;

import co.javeriana.ws.rest.entities.Trip;
import co.javeriana.ws.rest.util.DatabaseConnection;
import co.javeriana.ws.rest.util.TripRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("/trips")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Trip> getAllTrips() {
        List<Trip> trips = null;
        try {
            Connection con = DatabaseConnection.getDatabaseConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from trips");
            trips = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String departure = rs.getString(3);
                String arrival = rs.getString(4);
                Date departure_date = rs.getDate(5);
                Date arrival_date = rs.getDate(6);
                trips.add(new Trip(id, name, departure, arrival, departure_date, arrival_date));
            }
            rs.close();
            stmt.close();
            con.close();
            return trips;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @DELETE
    @Path("/trips/{idTrip}")
    public Response deleteTrip(@PathParam("idTrip") int idTrip) {
        try {
            Connection con = DatabaseConnection.getDatabaseConnection();
            Statement stmt = con.createStatement();
            if (!verifyExistentTrip(idTrip, stmt)) {
                return Response.status(Response.Status.NOT_FOUND).entity("El viaje con ID: " + idTrip+" no existe").build();
            }
            ResultSet rs = stmt.executeQuery("DELETE FROM trips WHERE id_trip=" + idTrip + ";");
            rs.close();
            stmt.close();
            con.close();
            return Response.status(Response.Status.OK).entity("El viaje ha sido eliminado exitosamente").build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/trips/{idTrip}")
    public Response updateTrip(@PathParam("idTrip") int idTrip,@QueryParam("departure") String departure,@QueryParam("arrival") String arrival){
        try {
            Connection con = DatabaseConnection.getDatabaseConnection();
            Statement stmt = con.createStatement();
            if (!verifyExistentTrip(idTrip, stmt)) {
                stmt.close();
                con.close();
                return Response.status(Response.Status.NOT_FOUND).entity("El viaje con ID: " + idTrip+" no existe").build();
            }
            ResultSet rs = stmt.executeQuery("UPDATE trips SET departure='"+departure+"', arrival='"+arrival+"' WHERE id_trip="+idTrip+";");
            rs.close();
            stmt.close();
            con.close();
            return Response.status(Response.Status.OK).entity("El viaje se ha actualizado exitosamente").build();
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/trips")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response createTrip(TripRequest request){
        Date departure_Date = null;
        Date arrival_Date = null;
        try {
            departure_Date = parseDate(request.getDeparture_date());
            arrival_Date = parseDate(request.getArrival_date());
        } catch (ParseException e) {
            return Response.status(Response.Status.FORBIDDEN).entity("El formato de las fechas no es válido").build();
        }
        try{
            if(!validateOrder(departure_Date,arrival_Date)){
                return Response.status(Response.Status.FORBIDDEN).entity("La fecha de arrivo es antes que la de salida").build();
            }
            Connection con = DatabaseConnection.getDatabaseConnection();
            resetIcrement(con);
            String query = " insert into trips(name, departure, arrival, departure_date, arrival_date)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            java.sql.Date departureDate = new java.sql.Date(departure_Date.getTime());
            java.sql.Date arrivalDate = new java.sql.Date(arrival_Date.getTime());
            preparedStmt.setString (1, request.getName());
            preparedStmt.setString (2, request.getDeparture());
            preparedStmt.setString(3,request.getArrival());
            preparedStmt.setDate(4,departureDate);
            preparedStmt.setDate(5,arrivalDate);
            preparedStmt.execute();
            con.close();
            return Response.status(Response.Status.CREATED).entity("El viaje se ha creado exitosamente").build();
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Date parseDate(String stringDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        return date;
    }
    private boolean validateOrder(Date departure_Date, Date arrival_Date){
        return arrival_Date.after(departure_Date);
    }

    private void resetIcrement(Connection con){
        try {
            Statement stm = con.createStatement();
            ResultSet rs1 = stm.executeQuery("SELECT COUNT(*) FROM trips;");
            int size = 0;
            if(rs1 != null){
                rs1.last();
                size = rs1.getInt(1);
            }
            rs1.close();
            if (size==0){
                ResultSet rs2 = stm.executeQuery("ALTER TABLE trips AUTO_INCREMENT = 1;");
                rs2.close();
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private boolean verifyExistentTrip(int idTrip, Statement statement) {
        try {
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM trips WHERE id_trip=" + idTrip + ";");
            int size = 0;
            if(rs != null){
                rs.last();
                size = rs.getInt(1);
            }
            rs.close();
            return size==1;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
