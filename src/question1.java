import java.util.ArrayList;

public class question1 {

    public ArrayList<node> frontier = new ArrayList<>();
    public ArrayList<node> explored = new ArrayList<>();





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

                if (j != i) {
                    space1 = node.places.get(i);
                    space2 = node.places.get(j);

                    if (space2.size() == 0 && space1.size() != 0) {

                        node newNode = new node();

                        card lastCard1 = (card) space1.get(space1.size() - 1);

                        copyOfNode.get(i).remove(lastCard1);

                        copyOfNode.get(j).add(lastCard1);

                        newNode.setPlace(copyOfNode);

                    }
                }
            }

        }////

        return false; }
}

