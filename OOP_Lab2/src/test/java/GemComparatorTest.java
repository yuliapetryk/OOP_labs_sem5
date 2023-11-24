import data.Gem;
import data.GemComparator;
import data.VisualParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GemComparatorTest {
    @Test
    public void testComparator() {

        Gem gem1 = new Gem("Diamond", 1, "precious", "South Africa", 3.0, new VisualParameters("colorless", 0.9, 57));
        Gem gem2 = new Gem("Amethyst", 2, "semi-precious", "Brazil", 8.0, new VisualParameters("purple", 0.7, 33));
        Gem gem3 = new Gem("Ruby", 3, "precious", "Myanmar", 2.5, new VisualParameters("red", 0.95, 50));

        List<Gem> gemList = new ArrayList<>();
        gemList.add(gem1);
        gemList.add(gem2);
        gemList.add(gem3);

        GemComparator gemComparator = new GemComparator();
        Collections.sort(gemList, gemComparator);
        assertThat(gemList.get(0), equalTo(gem3));  // Ruby (2.5)
        assertThat(gemList.get(1), equalTo(gem1));  // Diamond (3.0)
        assertThat(gemList.get(2), equalTo(gem2));  // Amethyst (8.0)
    }

    @Test
    public void testComparatorWithEqualValues() {

        Gem gem1 = new Gem("Diamond", 1, "precious", "South Africa", 3.0, new VisualParameters("colorless", 0.9, 57));
        Gem gem2 = new Gem("Amethyst", 2, "semi-precious", "Brazil", 3.0, new VisualParameters("purple", 0.7, 33));
        Gem gem3 = new Gem("Ruby", 3, "precious", "Myanmar", 3.0, new VisualParameters("red", 0.95, 50));

        List<Gem> gemList = new ArrayList<>();
        gemList.add(gem1);
        gemList.add(gem2);
        gemList.add(gem3);

        GemComparator gemComparator = new GemComparator();

        Collections.sort(gemList, gemComparator);

        assertThat(gemList.get(0), equalTo(gem1));  // Diamond (3.0)
        assertThat(gemList.get(1), equalTo(gem2));  // Amethyst (3.0)
        assertThat(gemList.get(2), equalTo(gem3));  // Ruby (3.0)
    }
}
