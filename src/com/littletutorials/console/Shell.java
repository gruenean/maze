package com.littletutorials.console;

import java.io.*;
import java.util.*;

public class Shell
{
    private static final String NO_CONSOLE = "Error: Console unavailable";
    private static final String GREETINGS = "Welcome to the System. Please login.%n";
    private static final String DENIED_ATTEMPT = "Wrong user name or password [%1$d]%n";
    private static final String ACCESS_DENIED = "Access denied%n";
    private static final String ACCESS_GRANTED = "Access granted%n";
    private static final String UNKNOWN_COMMAND = "Unknown command [%1$s]%n";
    private static final String COMMAND_ERROR = "Command error [%1$s]: [%2$s]%n";

    private static final String TIME_FORMAT = "%1$tH:%1$tM:%1$tS";
    private static final String PROMPT = TIME_FORMAT + " $ ";
    private static final String USER_PROMPT = TIME_FORMAT + " User: ";
    private static final String PASS_PROMPT = TIME_FORMAT + " Password [%2$s]: ";

    private static final String USER = "john";
    private static final String PASS = "secret";

    public static void main(String[] args)
    {
        Console console = System.console();
        if (console != null)
        {
            if (login(console))
            {
                execCommandLoop(console);
            }
        }
        else
        {
            throw new RuntimeException(NO_CONSOLE);
        }
    }

    private static boolean login(Console console)
    {
        console.printf(GREETINGS);

        boolean accessGranted = false;
        int attempts = 0;
        while (!accessGranted && attempts < 3)
        {
            String name = console.readLine(USER_PROMPT, new Date());
            char[] passdata = console.readPassword(PASS_PROMPT, new Date(), name);
            if (USER.equals(name) && PASS.equals(new String(passdata)))
            {
                attempts = 0;
                accessGranted = true;
                break;
            }

            console.printf(DENIED_ATTEMPT, ++attempts);
        }

        if (! accessGranted)
        {
            console.printf(ACCESS_DENIED);
            return false;
        }

        console.printf(ACCESS_GRANTED);
        return true;
    }

    private static void execCommandLoop(final Console console)
    {
        while (true)
        {
            String commandLine = console.readLine(PROMPT, new Date());
            Scanner scanner = new Scanner(commandLine);

            if (scanner.hasNext())
            {
                final String commandName = scanner.next().toUpperCase();

                try
                {
                    final Command cmd = Enum.valueOf(Command.class, commandName);
                    String param = scanner.hasNext() ? scanner.next() : null;
                    cmd.exec(console, new String[]{param}, new Command.Listener()
                    {
                        @Override
                        public void exception(Exception e)
                        {
                            console.printf(COMMAND_ERROR, cmd, e.getMessage());
                        }
                    });
                }
                catch (IllegalArgumentException e)
                {
                    console.printf(UNKNOWN_COMMAND, commandName);
                }
            }

            scanner.close();
        }
    }
}