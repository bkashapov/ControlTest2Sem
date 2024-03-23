public class TVprogram {
    public Channel channel;
    public String title;
    public BroadcastsTime time;

    @Override
    public String toString() {
        return title + " " + time.toString();
    }
}
