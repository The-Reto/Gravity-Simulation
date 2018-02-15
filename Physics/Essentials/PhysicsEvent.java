package Physics.Essentials;

import Graphics.UI.Feed;

public class PhysicsEvent {

    public enum EventType{
        COLLISION,
        NEW_OBJECT,
        RESET,
        READY
    }

    EventType myType;
    String message;
    Time time;

    public PhysicsEvent(EventType myType, String message, Time time){
        this.myType = myType;
        this.message = message;
        this.time = time;
        Feed.insert(this);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %s", time.toShortString(), myType, message);
    }
}
