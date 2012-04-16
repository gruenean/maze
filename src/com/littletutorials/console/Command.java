package com.littletutorials.console;

import java.io.Console;

public enum Command
{
    BYE(new Action()
    {
        @Override
        public void exec(Console c, String[] params)
        {
            c.printf("Bye%n");
            System.exit(0);
        }
    }),
    DETAILS(new Action()
    {
        @Override
        public void exec(Console c, String[] params) throws Exception
        {
            int detailsLevel = 1;
            try
            {
                detailsLevel = Integer.parseInt(params[0]);
            }
            catch (NumberFormatException e)
            {
                // ignore
            }

            for (int i = 1; i <= detailsLevel; i++)
            {
                c.printf("Detail number %1$X%n", i);
            }
        }
    });

    private interface Action
    {
        public void exec(Console c, String[] params) throws Exception;
    }

    public interface Listener
    {
        public void exception(Exception e);
    }

    private Action action;

    private Command(Action a)
    {
        this.action = a;
    }

    public void exec(final Console c, final String[] params, final Listener l)
    {
        try
        {
            action.exec(c, params);
        }
        catch (Exception e)
        {
            l.exception(e);
        }
    }
}