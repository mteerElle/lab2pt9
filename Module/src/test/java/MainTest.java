import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void addSix() {
        assertEquals(45, Main.addSix(39));
    }
    @Test
    void daysInMonth(){
        assertEquals(28, Main.daysInMonth(2));
        assertEquals(31, Main.daysInMonth(12));
    }
    @Test
    void tomorrow(){
        Main.date expect= new Main.date(1,1,2001);
        Main.date idekman = new Main.date(31,12,2000);
        assertEquals( expect , Main.tomorrow(idekman));
    }
    @Test
    void dayOfYear(){
        Main.date thedate = new Main.date(2,2,2000);
        assertEquals(32, Main.dayOfYear(thedate));
    }
    @Test
    void comesBefore(){
        Main.date before = new Main.date(1,1,1);
        Main.date afta = new Main.date(2,1,1);
        assertEquals(true,Main.comesBefore(before, afta));
    }
    @Test
    void dateIntervalDays(){
        /
    }
    @Test
    void dateOverlap(){
        //???/
    }
    @Test
    void dateIntervalIntersect(){

    }
    @Test
    void nullableDateIntervalIntersect(){

    }
}
