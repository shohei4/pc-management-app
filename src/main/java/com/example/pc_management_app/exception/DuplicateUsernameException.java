package com.example.pc_management_app.exception;

public class DuplicateUsernameException extends RuntimeException{
	
	private final String username;
	
	public DuplicateUsernameException(String username) {
        super("このユーザー名は既に使われています: " + username);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
