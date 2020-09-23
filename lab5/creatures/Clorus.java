package creatures;


import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    private int r, g, b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        Clorus newClorus = new Clorus(energy/2.0);
        energy /= 2.0;
        return newClorus;
    }

    @Override
    public void stay() {
        energy += 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipLocations = new ArrayDeque<>();

        //Rule 1:
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.add(d);
            }

            if (neighbors.get(d).name().equals("plip")) {
                plipLocations.add(d);
            }
        }

        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        //Rule 2:
        if (plipLocations.size() > 0) {
            Direction randomPlipDirection = randomEntry(plipLocations);
            return new Action(Action.ActionType.ATTACK, randomPlipDirection);
        }

        //Rule 3:
        Direction randomEmptySquare = randomEntry(emptyNeighbors);
        if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, randomEmptySquare);
        }

        return new Action(Action.ActionType.MOVE, randomEmptySquare);
    }

    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }


}
