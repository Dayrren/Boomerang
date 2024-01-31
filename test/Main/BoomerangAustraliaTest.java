package Main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoomerangAustraliaTest {

    @Test
    void testPlayerRange() {

        //5
        assertEquals(
                "invalid player range",
                assertThrows(Error.class, () -> {
                    BoomerangAustralia.main(new String[]{"2", "3"});
                }).getMessage()
        );

        //4
        Assertions.assertDoesNotThrow(() -> {
            BoomerangAustralia.main(new String[]{"0", "4"});
        });

        //2
        Assertions.assertDoesNotThrow(() -> {
            BoomerangAustralia.main(new String[]{"0", "2"});
        });

        //1
        assertEquals(
                "invalid player range",
                assertThrows(Error.class, () -> {
                    BoomerangAustralia.main(new String[]{"1", "0"});
                }).getMessage()
        );

    }

    @Test
    void testInvalid() {

        //non existent content pack
        assertEquals(
                "invalid ContentPack",
                assertThrows(Error.class, () -> {
                    BoomerangAustralia.main(new String[]{"2", "2", "Bobstralia"});
                }).getMessage()
        );

        //no ip

        assertEquals(
                "invalid params",
                assertThrows(Error.class, () -> {
                    BoomerangAustralia.main(new String[]{});
                }).getMessage()
        );
    }
}