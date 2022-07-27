public class Wall extends Tile{

    //constructor
    public Wall(){
       super('#');
   }

   //methods
    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
