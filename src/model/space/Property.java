package model.space;

import model.Player;

public interface Property {
    int getPrice();

    void soldTo(Player player);

    Player getOwner();
}