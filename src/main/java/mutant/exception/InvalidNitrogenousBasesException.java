package mutant.exception;

/**
 * Exception created to handle errors when an invalid nitrogenous base is
 * given to system.
 * 
 * @author Hélio De Rosa Junior
 */
public class InvalidNitrogenousBasesException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvalidNitrogenousBasesException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
