package com.siwei.carmen;

import junit.framework.TestCase;

/**
 * Created by tux on 12/6/15.
 */
public class CardTest extends TestCase {
    private Card c1;
    private Card c2;
    private Card c3;

    public void setUp() throws Exception {
        super.setUp();
        c1 = new Card(-1,"a", 12, 5);
        c2 = new Card(-1,"a", 1, 9);
        c3 = new Card(-1,"a", 1, 30);
    }

    public void tearDown() throws Exception {

    }

    public void testDaysOfMonth() throws Exception {
        assertEquals(Card.daysOfMonth(2015, 2), 28);
        assertEquals(Card.daysOfMonth(2016, 2), 29);
        assertEquals(Card.daysOfMonth(2016, 3), 31);
    }

    public void testGetMaxIFP() throws Exception {
        assertEquals(c1.getMaxIFP(), 52);
        assertEquals(c2.getMaxIFP(), 37);
        assertEquals(c3.getMaxIFP(), 58);
    }

    public void testGetMinIFP() throws Exception {
        assertEquals(c1.getMinIFP(), 23);
        assertEquals(c2.getMinIFP(), 8);
        assertEquals(c3.getMinIFP(), 29);
    }
}