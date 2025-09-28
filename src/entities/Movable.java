package entities;
interface Movable {
    void move();
    void setVelocity(int x, int y);
    int getVelocityX();
    int getVelocityY();
}