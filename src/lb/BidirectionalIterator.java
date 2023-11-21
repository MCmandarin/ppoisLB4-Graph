package lb;

import java.util.Iterator;

public interface BidirectionalIterator<T> extends Iterator<T> {
    boolean hasPrevious();

    T previous();
}
