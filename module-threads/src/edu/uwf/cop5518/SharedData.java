package edu.uwf.cop5518;

/**
 * Models a shared data object.
 *
 * @author Thomas Reichherzer
 */

public class SharedData {
    private int _g = 0;
    private int _maxValue;

    private static int MAX_VALUE = 3;

    public SharedData(int maxValue) {
        _maxValue = maxValue;
    }

    public SharedData() {
        _maxValue = MAX_VALUE;
    }

    public void incrementValue() {
        _g++;
    }

    public int getValue() {
        return _g;
    }

    public int getRange() {
        return _maxValue;
    }
}
