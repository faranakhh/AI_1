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

                        String action = lastCard1.number + "" + lastCard1.color + " moves from " + i+1 + " to " + j+1;
                        System.out.println("new node is created : " + action);
                        System.out.println("//////////////");
                        newNode.depth = node.depth + 1;
                        newNode.parent = node;
                        newNode.action = action;
                        newNode.actions = (ArrayList<String>) (node.actions).clone();
                        newNode.actions.add(action);
                        newNode.showThePlaces();
                        if (!checkRedundancy(newNode)) {

                            frontier.add(newNode);
                            newNodes.add(newNode);
                        } else {

                            System.out.println("we had this node before!");
                        }



                    }

                }
            }

        }///







        return false; }
    public boolean checkRedundancy(node node) {

        for (int i = 0; i < frontier.size(); i++) {

           for( int j = 0; j < frontier.get(i).places.size(); j++) {
               if ((!frontier.get(i).getNumberOfAPlace(i).equals(node.getNumberOfAPlace(i))) || (!node.getColorOfAplace(i).equals(node.getColorOfAplace(i)))) {
                   return false;
               }
              else return true;
           }
        }
        for (int i = 0; i < explored.size(); i++) {
            for (int j= 0; i < explored.get(i).places.size(); j++) {

                if ((!explored.get(i).getNumberOfAPlace(i).equals(node.getNumberOfAPlace(i))) || (!node.getColorOfAplace(i).equals(node.getColorOfAplace(i)))) {
                    return false;
                }
                else return true;

            }


        }

        return false;
    }


    }



