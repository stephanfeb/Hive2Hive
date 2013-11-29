package org.hive2hive.core.client.menu;

import org.hive2hive.core.client.H2HConsole;
import org.hive2hive.core.client.SessionInstance;
import org.hive2hive.core.security.UserCredentials;

public class UserMenu extends ConsoleMenu {

	public UserMenu(H2HConsole console, SessionInstance session) {
		super(console, session);
	}

	@Override
	protected void addMenuItems() {

		add(new H2HConsoleMenuItem("Set User ID") {
			protected void execute() throws Exception {
				System.out.println("Specify the user ID:");
				session.setUserId(awaitStringParameter());
				printSuccess();
			}
		});
		add(new H2HConsoleMenuItem("Set User Password") {
			protected void execute() throws Exception {
				System.out.println("Specify the user password:");
				session.setPassword(awaitStringParameter());
				printSuccess();
			}
		});
		add(new H2HConsoleMenuItem("Set User PIN") {
			protected void execute() throws Exception {
				System.out.println("Specify the user PIN:");
				session.setPin(awaitStringParameter());
				printSuccess();
			}
		});
		add(new H2HConsoleMenuItem("Create User Credentials") {
			@Override
			protected boolean preconditionsSatisfied() {
				boolean satisfied = true;
				if (session.getUserId() == null){
					System.out.println("User Credentials cannot be created: User ID not yet set.");
					satisfied = false;
				}
				if (session.getPassword() == null){
					System.out.println("User Credentials cannot be created: User password not yet set.");
					satisfied = false;
				}
				if (session.getPin() == null){
					System.out.println("User Credentials cannot be created: User PIN not yet set.");
					satisfied = false;
				}
				return satisfied;
			}
			protected void execute() throws Exception {
				session.setCredentials(new UserCredentials(session.getUserId(), session.getPassword(),
						session.getPin()));
				printSuccess();
			}
		});
	}

	@Override
	protected String getInstruction() {
		return "Please select a user configuration option:\n";
	}
}
