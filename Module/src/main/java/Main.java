//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // add six to a number
    public static int addSix(int a){
        return a + 6;
    }

    static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Hello and welcome!"));


    }
    //this record the date
    public record date(int day, int month, int year) {
        public date {
            if(day > daysInMonth(month)) {
                throw new IllegalArgumentException("Not valid date dumbass");
            }

        }
    }

    date date1 = new date(1,1,2001);
    date date2 = new date(2,2,2002);
    date date3 = new date(3,3,2003);

    //returns amount of days in given month
    public static int daysInMonth(int month){
        if((month<=0)||(month>12) ){
            throw new IllegalArgumentException("Thats not a month dumbass");
        }
        else if(month ==2){
            return 28;
        }
        else if ((month==4)|| (month ==6)||(month== 9)||(month==11)){
            return 30;
        }
        else {
            return 31;
        }
    }
    //returns the next date for the day after date
    public static date tomorrow(date today){
        int day;
        int mon;
        int yr;
        if(today.day() == daysInMonth( today.month() )){
            day =1;
            if(today.month() ==12){
                yr = today.year() +1;
                mon = 1;
                return new date(day,mon,yr);
            }
            mon=today.month()+1;
            yr = today.year();
            return new date(day,mon,yr);
        }
        day = today.day() +1;
        mon = today.month();
        yr = today.year();
        return new date(day,mon,yr);

    }
    // return num day since jan first (noon of jan to noon of date)
    public static int dayOfYear(date today){
        int days = 0;
        for(int i = 1; i < today.month(); i++){
            days+=daysInMonth(i);
        }
        days+=today.day();
        return days-1;
    }
    //return true if day 1 comes before day 2, if not, false
    public static boolean comesBefore(date day1, date day2){
        if (day1.year() == day2.year()){
            if(day1.month()==day2.month()){
                if(day1.day() < day2.day()){
                    return true;
                }
                return false;
            }
            else {
                if(day1.month()< day2.month()){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else{
            if(day1.year() < day2.year()){
                return true;
            }
            else{
                return false;
            }
        }
    }

    //records date intervals man idek
    public record dateInterval( date start, date end){
        public dateInterval{
            if (comesBefore(start,end) == false){
                throw new IllegalArgumentException("ts isn't in right order cuzzo");
            }
        }

    }


    dateInterval d1 = new dateInterval(date1,date2);
    dateInterval d2 = new dateInterval(date2,date3);
    dateInterval d3 = new dateInterval(date1, date3);

    //returns num of days between intervals DO TEST
    public static int dateIntervalDays(dateInterval inter){
        return dayOfYear(inter.end()) - dayOfYear(inter.start());
    }

    // returns true if periods synch at least once, false if not DO TEST FOR
    public static boolean dateOverlap( dateInterval int1, dateInterval int2){
        return comesBefore(int1.start(),int2.end()) && comesBefore(int2.start(), int1.end());
    }
    //---- do maybe date ranges 3.3

    static dateInterval dateIntervalIntersect(dateInterval first, dateInterval second){
           if(!dateOverlap(first,second)){
               return null;
           }
           date start;
           if(comesBefore(first.start(),second.start())){
               start = second.start();
           }
           else{
               start = first.start();
           }
           date end;
           if(comesBefore(first.end(),second.end())){
               end = first.end();
           }
           else{
               end = second.end();
           }
           return new dateInterval(start,end);

    }

    public static dateInterval nullableDateIntervalIntersect(dateInterval first, dateInterval second){
        if((first == null)||(second==null)){
            return null;
        }
        if(!dateOverlap(first, second)){
            return null;
        }
        else{
            return dateIntervalIntersect(first,second);
        }
    }




    // STOPPED HERE HAVE NO IDEA HOW TO DO THIS COOL
    // how to represent linked list dates??? ERROR HERE CHECk
    public record DateList(date first, DateList rest){}


    // returns length of lest of dates
    public static int listLen( DateList dates ){
        return switch (dates){
            case null -> 0;
            case DateList( date first , DateList rest) -> 1 + listLen(rest);
        };
    }
    // returns smallest dat in list of dates
    static date minDate( DateList dates){
         switch(dates){
             case null -> {return null;}
            case DateList(date first, DateList rest)-> {
                date restMin = minDate(rest);
                if (restMin == null) {
                    return first;
                }
                if (comesBefore(first, restMin)) {
                    return first;
                }
                else {
                    return restMin;
                }
            }
        }
    }
    // returns largest date in list of dates

    static date maxDate(DateList dates){
        switch (dates) {
            case null -> {
                return null;
            }
            case DateList(date first, DateList rest) -> {
                date restMax = maxDate(rest);
                if(restMax == null){
                    return first;
                }
                if (comesBefore(first, restMax)){
                    return restMax;
                }
                else{
                    return first;
                }
            }
        }
    }
    //returns shortest date interval
    static dateInterval dateCover(DateList list){
        date min = minDate(list);
        date max = maxDate(list);

        if(min==null){
            return null;
        }
        return new dateInterval(min,max);
    }
    //returns new list where date is mapped to following date whatever that means ig
    static DateList allTomorrows(DateList dates){
        switch (dates){
            case null ->{
                return null;
            }
            case DateList(date first, DateList rest) -> {
                return new DateList(tomorrow(first), allTomorrows(rest));
            }
        }
    }

    // returns list wit new end on it
    static DateList addToEnd(DateList dates, date newDate){
        switch(dates){
            case null -> {
                return new DateList(newDate, null);
            }
            case DateList(date first, DateList rest)->{
                return new DateList(first, addToEnd(rest, newDate));
            }
        }
    }
    // creates new list including all elements of 2 lists
    static DateList append(DateList first, DateList second){
        switch(first){
            case null ->{
                return second;
            }
            case DateList(date firstDate, DateList rest)->{
                return new DateList(firstDate, append(rest, second));
            }
        }
    }



}
