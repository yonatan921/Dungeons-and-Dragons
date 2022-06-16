public class Empty extends Tile{
    //constructor
    protected Empty() {
        super('_');
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
