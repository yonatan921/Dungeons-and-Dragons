package Units;

public abstract class Units {
    //fields
    protected String name;
    protected Integer health_pool;
    protected Integer health_amount;
    protected Integer attack_points;
    protected Integer defence_points;

    protected Character tile;

    //constructors
    public Units(String name, Integer health_pool, Integer attack_points, Integer defence_points, Character tile){
        this.name = name;
        this.health_pool = health_pool;
        this.health_amount = health_pool;
        this.attack_points = attack_points;
        this.defence_points = defence_points;
        this.tile = tile;
    }

    //methods
    public String tile_toString(){
        return tile.toString();
    }

    public String unit_getName(){
        return name;
    }

    public String unit_description(){ //TODO: override in each class and fix this method
        return name + " " + health_amount;
    }

}
