public class Player {

    private String name;
    private int health;
    private float potions;

    private final int max_health;

    public Player(String name) {
        this.name = name;
        this.health = (int) (Math.random() * 51) + 50;
        this.potions = (float) Math.floor((double) (Math.random() * 2) + 1);
        this.max_health = this.health;
    }

    public float usePotion() {
        float drank = (float) (Math.random() * 1);
        if(drank > this.potions){
            drank = this.potions;
        }
        this.potions -= drank;
        if(this.health + (int) (drank * 50) > this.max_health){
            this.health = this.max_health;
            return drank;
        }
        this.health += (int) (drank * 50);
        return drank;
    }

    public int attack(Monster monster) {
        int damage = (int) (Math.random() * this.max_health) + 1;
        monster.sufferDmg(damage);
        return damage;
    }

    public void sufferDmg(int damage) {
        this.health = health - damage;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public float getPotions() {
        return this.potions;
    }

    public float addPotions(int potions) {
        this.potions += potions;
        return this.potions;
    }

    public int getMaxHealth() {
        return this.max_health;
    }
   
}
