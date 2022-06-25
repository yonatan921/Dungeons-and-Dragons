public class Empty extends Tile{
    //constructor
    protected Empty() {
        super('.');
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
