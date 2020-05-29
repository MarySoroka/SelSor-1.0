package model;

import model.space.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable, Movable {
    private ArrayList<UtilitySpace> utilities;
    private ArrayList<EstateSpace> estates;
    private ArrayList<RailroadSpace> railroads;
    private int asset;
    private int breakJailCards;
    private boolean isJailed;
    private int money;
    private int ID;
    private int x = 360;
    private int y = 460;

    public Player(int startingMoney, int ID) {
        money = startingMoney;
        asset = startingMoney;
        utilities = new ArrayList<>();
        estates = new ArrayList<>();
        railroads = new ArrayList<>();
        this.ID = ID;
        breakJailCards = 0;
        isJailed = false;
    }

    public void buy(Property property) {
        if (property instanceof UtilitySpace) {
            utilities.add((UtilitySpace) property);
        } else if (property instanceof RailroadSpace) {
            railroads.add((RailroadSpace) property);
        } else {
            estates.add((EstateSpace) property);
        }

        property.soldTo(this);
        pay(property.getPrice());
        asset += property.getPrice() / 2;
    }

    public void buyHouse(EstateSpace estateSpace, int houseCount) {
        int price = estateSpace.getHousePrice() * houseCount;

        pay(price);
        estateSpace.buildHouse(houseCount);
        asset += price / 2;
    }

    public void buyLandmark(EstateSpace estateSpace) {
        int price = estateSpace.getLandmarkPrice();

        pay(price);
        estateSpace.buildLandmark();
        asset += price / 2;
    }

    public void pay(double amount) {
        money -= amount;
        asset -= amount;
    }

    public void getPaid(double amount) {
        money += amount;
        asset += amount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAsset() {
        return asset;
    }

    public int getID() {
        return ID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBreakJailCards() {
        return breakJailCards;
    }

    public void jailed() {
        isJailed = true;
    }

    public void getOutOfJail() {
        isJailed = false;
    }

    public boolean isJailed() {
        return isJailed;
    }

    public int getUtilityCount() {
        return utilities.size();
    }

    public int getRailroadCount() {
        return railroads.size();
    }

    public void drawBreakJailCard() {
        breakJailCards += 1;
    }

    public ArrayList<PropertySpace> getAllProperty() {
        ArrayList<PropertySpace> property = new ArrayList<>();
        property.addAll(estates);
        property.addAll(utilities);
        property.addAll(railroads);

        return property;
    }
}
