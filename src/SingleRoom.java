// Room.java
abstract class Room {
    public abstract String getType();
    public abstract double getPrice();
}

class SingleRoom extends Room {
    @Override
    public String getType() {
        return "Single Room";
    }

    @Override
    public double getPrice() {
        return 100.0; // цена за ночь
    }
}

class DoubleRoom extends Room {
    @Override
    public String getType() {
        return "Double Room";
    }

    @Override
    public double getPrice() {
        return 150.0; // цена за ночь
    }
}

class Suite extends Room {
    @Override
    public String getType() {
        return "Suite";
    }

    @Override
    public double getPrice() {
        return 250.0; // цена за ночь
    }
}

class DeluxeRoom extends Room {
    @Override
    public String getType() {
        return "Deluxe Room";
    }

    @Override
    public double getPrice() {
        return 350.0; // цена за ночь
    }
}
