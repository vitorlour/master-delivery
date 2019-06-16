package br.com.masterdelivery.service.exception;

/**
 * @author vitorlour
 *
 */

public class LocationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LocationException(String msg) {
		super(msg);
	}
	
	public LocationException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
