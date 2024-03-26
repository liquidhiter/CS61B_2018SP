import static org.junit.Assert.*;

import org.junit.Test;

public class TestQuickUnionDS {
    
    @Test
    public void testConnect() {
        QuickUnionDS union = new QuickUnionDS(10);
        union.connect(1, 2);
        assert(union.isConnected(1, 2));
        union.connect(0, 1);
        assert(union.isConnected(0, 1));
        assert(union.isConnected(1, 2));
    }
}
