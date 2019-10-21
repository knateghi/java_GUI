package com.fawad.sayed;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ball extends JApplet implements Runnable
{
    private Thread blueBall;
    private boolean xUp, yUp, bouncing;
    private int x, y, xDx, yDy;
    private final int MAX_X = 200;
    private final int MAX_Y = 200;

    public void init()
    {
        xUp = false;
        yUp = false;
        xDx = 1;
        yDy = 1;
        bouncing = false;


        addMouseListener(

                new MouseListener()
                {

                    public void mousePressed( MouseEvent event )
                    {
                        generateBall( event );
                    }
                    public void mouseExited( MouseEvent event ) {}
                    public void mouseClicked( MouseEvent event ) {}
                    public void mouseReleased( MouseEvent event ) {}
                    public void mouseEntered( MouseEvent event ) {}
                }
        )
        ;

        setSize( MAX_X, MAX_Y );
    }

    private void generateBall( MouseEvent event )
    {
        if ( blueBall == null )
        {
            x = event.getX();
            y = event.getY();
            blueBall = new Thread( this );

            bouncing = true; // start ball's bouncing
            blueBall.start();
        }
    }

    public void stop()
    {
        blueBall = null;
    }

    public void paint( Graphics g )
    {
        super.paint( g );

        if ( bouncing )
        {
            g.setColor( Color.blue );
            g.fillOval( x, y, 25, 25 );
        }
    }

    public void run()
    {

        while ( true )
        {


            try
            {
                blueBall.sleep( 20 );
            }

            catch( InterruptedException exception )
            {

                System.err.println( exception.toString() );
            }

            if ( xUp == true )
                x += xDx;
            else
                x -= xDx;

            if ( yUp == true )
                y += yDy;
            else
                y -= yDy;

            if ( y <= 0 )
            {
                yUp = true;
                yDy = ( int ) ( Math.random() * 5 + 2 );
                yUp = false;
            }

            if ( x <= 0 )
            {
                xUp = true;
                xDx = ( int ) ( Math.random() * 5 + 2 );
            }

            else if ( x >= MAX_X - 10 )
            {
                xUp = false;
                xDx = ( int ) ( Math.random() * 5 + 2 );
            }

              repaint();

        }

    }

}
