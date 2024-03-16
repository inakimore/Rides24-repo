package businessLogic;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Ride;
import domain.User;
import domain.Passenger;
import domain.Request;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.RideAlreadyExistException;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		
		
		dbManager=new DataAccess();
		    
		//dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    
    /**
     * {@inheritDoc}
     */
    @WebMethod public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(from);		

		dbManager.close();
		
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	   
		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, driverEmail);		
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

    
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}
	
	// NUESTROS MÉTODOS DEL 1 ITERAZIOA
	
	// 
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public boolean isLogin(String email){
		dbManager.open();
		boolean alreadyRegistered = dbManager.isLogin(email);
		dbManager.close();
		return alreadyRegistered;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public boolean checkPassword(String email, String password){
		dbManager.open();
		boolean checkPassword = dbManager.checkPassword(email, password);
		dbManager.close();
		return checkPassword;
	}
	
	public boolean createUser(String name, int tlf_number, String email, String password, boolean isDriver) {
		dbManager.open();
		boolean createdUser = dbManager.createUser(name, tlf_number, email, password, isDriver);
		dbManager.close();
		return createdUser;
	}
	
	public User requestUser(String email) {
		dbManager.open();
		User user = dbManager.requestUser(email);
		dbManager.close();
		return user;
	}
	
	public float updateAmount(User user, float floatValue, boolean isAtera) {
		dbManager.open();
		float updateAmount = dbManager.updateAmount(user, floatValue, isAtera);
		dbManager.close();
		return updateAmount;
	}
	
	public void requestRide(Ride selectedRide, Passenger passenger) {
		dbManager.open();
		dbManager.requestRide(selectedRide, passenger);
		dbManager.close();
	}
	
	@Override
	public void acceptDeny(Request request, boolean accepted) {
		dbManager.open();
		dbManager.acceptDeny(request, accepted);
		dbManager.close();
	}
	
	 public boolean validAmount(User user, float floatValue) {
		dbManager.open();
		boolean res = dbManager.validAmount(user, floatValue);
		dbManager.close();
		return res;
	 }
	
	// AQUI TERMINAN LOS QUE HEMOS CREADO NOSOSTROS
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();
		dB4oManager.close();
	}
	

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}

}

