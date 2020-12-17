import java.util.ArrayList;

public class question1 {

    public ArrayList<node> frontier = new ArrayList<>();
    public ArrayList<node> explored = new ArrayList<>();

    node node;
    public void copy(){}

    public boolean expanding(node node) {
        ArrayList<node> newNodes = new ArrayList<>();
        ArrayList space1;
        ArrayList space2;

        for (int i = 0; i < node.places.size(); i++) {

            for (int j = 0; j < node.places.size(); j++) {

                ArrayList<ArrayList<card>> copyOfNode = new ArrayList<>();

                for (ArrayList<card> aPlace : node.places) {

                    copyOfNode.add((ArrayList<card>) aPlace.clone());
                }


            }
        }
        return  false;}///////

 }