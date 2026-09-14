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
        Main.dateInterval int1 = new Main.dateInterval(new Main.date(1,1, 2020), new Main.date(1,1,2020));
        Main.dateInterval int2 = new Main.dateInterval(new Main.date(1,1, 2020), new Main.date(10,1,2020));
        assertEquals(0, Main.dateIntervalDays(int1));
        assertEquals(9, Main.dateIntervalDays(int2));

    }
    @Test
    void dateOverlap(){



    }
    @Test
    void dateIntervalIntersect(){

    }
    @Test
    void nullableDateIntervalIntersect(){

    }
    @Test
    void listLen(){
        assertEquals(0,Main.listLen(null));
        Main.DateList first = new Main.DateList(new Main.date(1,1,2026),null);
        assertEquals(1,Main.listLen(first));

    }

    @Test
    void minDate(){
        Main.date d1 = new Main.date(1,1,2026);
        Main.date d2 = new Main.date(1,2,2026);
        Main.date d3 = new Main.date(1,3,2026);
        Main.DateList list = new Main.DateList(d1,new Main.DateList(d3, new Main.DateList(d2, null)));
        assertEquals(null, Main.minDate(null));
        assertEquals(d1,Main.minDate(new Main.DateList(d1,null)));
        assertEquals(d1,Main.minDate(list));

    }
    @Test
    void maxDate(){
        Main.date d1 = new Main.date(1,1,2026);
        Main.date d2 = new Main.date(1,2,2026);
        Main.date d3 = new Main.date(1,3,2026);
        Main.DateList list = new Main.DateList(d1,new Main.DateList(d3, new Main.DateList(d2, null)));
    }


}
