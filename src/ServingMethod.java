
public interface ServingMethod {

	void runStartOfTurn();
	void runEndOfTurn();
	void updateCustomers();
	int getTurn();
	int customersServed();
	double earnings();
	
}
