package Physics.Essentials;

public class Time {
    private double seconds;

    public Time(double startTime){
        this.seconds = startTime;
    }

    public double getSeconds() {
        return seconds;
    }

    public void addSeconds(double seconds) {
        this.seconds += seconds;
    }

    @Override
    public String toString() {
        StringBuilder toRet = new StringBuilder();
        int minutes = (int) this.seconds / 60;
        double secs = this.seconds - 60 * minutes;
        int hours = minutes / 60;
        minutes = minutes - hours * 60;
        int days = hours / 24;
        hours = hours - days * 24;
        toRet.append(days).append(" Days, ");
        toRet.append(String.format("%02d Hours, ", hours));
        toRet.append(String.format("%02d Minutes and ", minutes));
        toRet.append(String.format("%02.2f Seconds", secs));
        return toRet.toString();
    }

    public String toShortString() {
        StringBuilder toRet = new StringBuilder();
        int mins = (int) this.seconds / 60;
        double secs = this.seconds - 60 * mins;
        int hours = mins / 60;
        mins = mins - hours * 60;
        int days = hours / 24;
        hours = hours - days * 24;
        toRet.append(String.format("D: %03d|", days));
        toRet.append(String.format("%02d:", hours));
        toRet.append(String.format("%02d:", mins));
        toRet.append(String.format("%04.1f", secs));
        return toRet.toString();
    }
}
