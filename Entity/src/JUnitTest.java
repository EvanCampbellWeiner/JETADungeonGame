
//import org.junit.Test;
//import static org.junit.Assert.*;
// or we can simply: import Money.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;


public class JUnitTest {

   @Test
    public void FloorTest() throws Exception {
        Floor MyHouse = new Floor(8, 8);

        Tile ShouldWork= MyHouse.GetTile(-2,-2);
        assertNotNull(ShouldWork);
    }

}


