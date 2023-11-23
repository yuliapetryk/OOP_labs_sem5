package data;

import java.util.Comparator;

public class GemComparator implements Comparator<Gem> {
    @Override
    public int compare(Gem gem1, Gem gem2) {
        return Double.compare(gem1.getValue(), gem2.getValue());
    }
}
