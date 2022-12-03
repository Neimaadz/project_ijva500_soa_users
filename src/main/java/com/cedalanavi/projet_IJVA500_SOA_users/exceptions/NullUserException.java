package com.cedalanavi.projet_IJVA500_SOA_users.exceptions;

public class NullUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullUserException() {
		super("L'utilisateur n'existe pas");
	}
}
