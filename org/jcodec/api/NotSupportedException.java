package org.jcodec.api;

public class NotSupportedException extends RuntimeException {

//	public NotSupportedException() {
//		super();
//	}
	
	private static final long serialVersionUID = 3007108065947759711L;

	public NotSupportedException(String... arguments) {
		super(""+arguments);
	}

}
