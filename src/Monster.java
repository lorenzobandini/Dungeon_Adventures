public class Monster {
    private int health;
    private int max_health;

    public Monster() {
        this.health = (int) (Math.random() * 91) + 10;
        this.max_health = this.health;
    }

    public int attack (Player player) {
        int damage = (int) (Math.random() * this.max_health) + 1;
        player.sufferDmg(damage);
        return damage;
    }

    public void sufferDmg(int damage) {
        this.health = health - damage;
    }

    public int getHealth() {
        return this.health;
    }

    public int getMaxHealth() {
        return this.max_health;
    }

    public int dropPotions() {
        int potions = (int) (Math.random() * (max_health/25));
        return potions;
    }

}
