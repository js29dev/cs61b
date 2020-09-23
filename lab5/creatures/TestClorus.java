package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestClorus {
    @Test
    public void testBasics() {
        Clorus c = new Clorus(1.0);
        assertEquals(1.0, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(0.97, c.energy(), 0.01);
        c.move();
        assertEquals(0.94, c.energy(), 0.01);
        c.stay();
        assertEquals(0.95, c.energy(), 0.01);
        c.stay();
        assertEquals(0.96, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus mom = new Clorus(1.0);
        Clorus offSpring = mom.replicate();
        double momEnergy = mom.energy();
        double offSpringEnergy = offSpring.energy();
        assertEquals(0.5, momEnergy, 0.01);
        assertEquals(0.5, offSpringEnergy, 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.0);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // STAY
        Plip p = new Plip(1.2);
        Plip pp = new Plip(1.5);
        Plip ppp = new Plip(1.5);
        Plip pppp = new Plip(1.5);
        HashMap<Direction, Occupant> topBottom = new HashMap<Direction, Occupant>();
        topBottom.put(Direction.TOP, p);
        topBottom.put(Direction.BOTTOM, pp);
        topBottom.put(Direction.LEFT, ppp);
        topBottom.put(Direction.RIGHT, pppp);

        actual = p.chooseAction(topBottom);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


//        // Energy >= 1; replicate towards an empty space.
//        p = new Plip(1.2);
//        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
//        allEmpty.put(Direction.TOP, new Empty());
//        allEmpty.put(Direction.BOTTOM, new Empty());
//        allEmpty.put(Direction.LEFT, new Empty());
//        allEmpty.put(Direction.RIGHT, new Empty());
//
//        actual = p.chooseAction(allEmpty);
//        Action unexpected = new Action(Action.ActionType.STAY);
//
//        assertNotEquals(unexpected, actual);
//
//
//        // Energy < 1; stay.
//        p = new Plip(.99);
//
//        actual = p.chooseAction(allEmpty);
//        expected = new Action(Action.ActionType.STAY);
//
//        assertEquals(expected, actual);
//
//
//        // Energy < 1; stay.
//        p = new Plip(.99);
//
//        actual = p.chooseAction(topEmpty);
//        expected = new Action(Action.ActionType.STAY);
//
//        assertEquals(expected, actual);
    }
}
