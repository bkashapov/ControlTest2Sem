public class BroadcastsTime implements Comparable<BroadcastsTime>{
    public BroadcastsTime(byte hour, byte minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }
    private byte hour;
    private byte minutes;
    @Override
    public int compareTo(BroadcastsTime t) {
        if (t.hour < hour) { return 1; }
        if (t.hour == hour) {
            if (t.minutes < minutes) {
                return 1;
            }
            else if(t.minutes == minutes) {
                return 0;
            }
        }
        return -1;

    }
    public byte hour() { return hour; }
    byte minutes() { return minutes; }
    boolean after(BroadcastsTime t) {
        return compareTo(t) > 0;
    }
    boolean befor(BroadcastsTime t) {
        return compareTo(t) < 0;
    }
    boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return compareTo(t1) > 0 && compareTo(t2) < 0;
    }

    @Override
    public String toString() {
        return hour + ":" + minutes;
    }
}
