
public class Customer {

	private int arrivalTurn;
	private int uid;
	private int orderTime;
	private double value;
	private int patienceLevel;
	private boolean orderTaken;

	/**
	 * Default constructor
	 */
	public Customer() {
		this.arrivalTurn = 0;
		this.uid = 0;
		this.orderTime = 0;
		this.patienceLevel = 0;
		this.orderTaken = false;
	}
	
	/**
	 * @param arrivalTurn
	 * @param uid
	 * @param orderTime
	 * @param patienceLevel
	 */
	public Customer(int arrivalTurn, int uid, int orderTime, double value, int patienceLevel) {
		this.arrivalTurn = arrivalTurn;
		this.uid = uid;
		this.value = value;
		this.orderTime = orderTime;
		this.patienceLevel = patienceLevel;
		this.orderTaken = false;
	}

	/**
	 * @return the arrivalTurn
	 */
	public int getArrivalTurn() {
		return arrivalTurn;
	}

	/**
	 * @param arrivalTurn the arrivalTurn to set
	 */
	public void setArrivalTurn(int arrivalTurn) {
		this.arrivalTurn = arrivalTurn;
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * @return the orderTime
	 */
	public int getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(int orderTime) {
		this.orderTime = orderTime;
	}

	/**
	 * @return the patienceLevel
	 */
	public int getPatienceLevel() {
		return patienceLevel;
	}

	/**
	 * @param patienceLevel the patienceLevel to set
	 */
	public void setPatienceLevel(int patienceLevel) {
		this.patienceLevel = patienceLevel;
	}
	
	/**
	 * @return the orderTaken
	 */
	public boolean isOrderTaken() {
		return orderTaken;
	}

	/**
	 * @param orderTaken the orderTaken to set
	 */
	public void setOrderTaken(boolean orderTaken) {
		this.orderTaken = orderTaken;
	}
	
	
}
