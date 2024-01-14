package tn.spring.exceptions;

public class ProjectNotFoundExeption extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	public ProjectNotFoundExeption(String message) {
		super(message);
	}

}
