package io.r2.j8p.t6_collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Develop code that uses diamond with generic declarations
 */
public class Diamond {

    public void diamond(List<Integer> param) {

        // no magic here, in new generic types are filled by type inference
        ArrayList<Integer> a = new ArrayList<>();

        // and this also works for parameters
        diamond(new ArrayList<>());

        // that's it
    }

}
