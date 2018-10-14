package java8;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: demo
 * @description: ${description}
 * @author: bang.chen
 * @create: 2018-10-13 18:25
 **/

public final class Track {

    private final String name;
    private final int length;

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the length of the track in milliseconds.
     */
    public int getLength() {
        return length;
    }

    public Track copy() {
        return new Track(name, length);
    }

}