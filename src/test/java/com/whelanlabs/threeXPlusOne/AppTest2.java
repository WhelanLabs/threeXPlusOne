package com.whelanlabs.threeXPlusOne;

import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest2 {

    @Test
    public void testApp() throws IOException
    {
    	Integer i = 28;
		Feeder f = new Feeder(i.toString());
    	App.process(i, f);
    }
    
}
