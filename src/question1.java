import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class question1 {

    public ArrayList<node> frontier = new ArrayList<>();
    public ArrayList<node> explored = new ArrayList<>();





    public boolean expanding(node node , int numbers) {
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
                        newNode.parent = node;
                        newNode.action = action;
                        newNode.depth = node.depth + 1;
                        newNode.actions = (ArrayList<String>) (node.actions).clone();
                        newNode.actions.add(action);
                        newNode.showThePlaces();
                        if (!checkRedundancy(newNode)) {

                            frontier.add(newNode);
                            newNodes.add(newNode);
                        } else {

                            System.out.println("we had this node before!");
                        }

                        if (isGoal(newNode,numbers)) {

                            System.out.println("we got the goal");
                            System.out.println("answer is founded in Depth of : " + newNode.depth);
                            System.out.println("number of actions:" + newNode.actions.size());
                            for (int p = 0; p < newNode.actions.size(); p++) {

                                System.out.println(newNode.actions.get(p));
                            }
                            int created = frontier.size() + explored.size();
                            System.out.println("created nodes:" + created);
                            System.out.println("Frontier size : " + frontier.size());
                            System.out.println("Expanded nodes: " + explored.size());


                            return true;
                        } else {
                            System.out.println("it is not goal yet");
                        }



                    }
                    if(space2.size() != 0 && space1.size() != 0){
                        card lastCard1 = (card) space1.get(space1.size()-1);
                        card lastCard2 = (card) space2.get(space2.size() - 1);
                        if(lastCard1.number < lastCard2.number){
                            copyOfNode.get(i).remove(lastCard1);
                            copyOfNode.get(j).add(lastCard1);
                            node newNode = new node();
                            newNode.setPlace(copyOfNode);
                            String nodeAction = lastCard1.number + "" + lastCard1.color + " moves from " + i+1 + " to " + j+1;
                            System.out.println("new node is created: " + nodeAction);
                            System.out.println("//////////");
                            newNode.depth = node.depth + 1;
                            newNode.action = nodeAction;
                            newNode.actions = (ArrayList<String>) (node.actions).clone();
                            newNode.actions.add(newNode.action);
                            newNode.parent = node;
                            newNode.showThePlaces();
                            if (!checkRedundancy(newNode)) {

                                frontier.add(newNode);
                                newNodes.add(newNode);
                            } else {

                                System.out.println("we had this node before");
                            }
                            if (isGoal(newNode, numbers)) {
                                System.out.println("we got the goal");
                                newNode.showThePlaces();
                                System.out.println("answer is founded in Depth of: " + newNode.depth);
                                System.out.println("number of actions:" +newNode.actions.size());
                                for (int p = 0; p < newNode.actions.size(); p++) {

                                    System.out.println(newNode.actions.get(p));
                                    int created = frontier.size() + explored.size();
                                    System.out.println("number of created nodes:" + created);
                                    System.out.println("Frontier size : " + frontier.size());
                                    System.out.println("Expanded nodes: " + explored.size());
                                    return true;


                                }


                            }
                            else {
                                System.out.println("it is not goal");
                            }


                        }
                    }


                }
            }

        }


        System.out.println("expanded");
        System.out.println("_________");


        node.expand = true;

        return false;
    }

        public  boolean isGoal(node node, int numbers){
            String color;

            boolean sorted = true;


            for (int i = 0; i < node.places.size(); i++) {

                if (node.places.get(i).size() != 0) {

                    color = node.places.get(i).get(0).color;

                    for (int j = 1; j < node.places.get(i).size(); j++) {

                        if ((node.places.get(i).get(j).color != color)) {

                            return false;
                        }//check all the cards in a place have the same color


                    }


                    ArrayList copy = new ArrayList(node.getNumberOfAPlace(i));
                    Collections.sort(copy, Collections.reverseOrder());
                    sorted = copy.equals(node.getNumberOfAPlace(i));//three lines above check if the cards sorted in a true way and they what we wanted
                    if (sorted == false) {
                        return false;
                    }

                }

                if (node.places.get(i).size() != 0 && node.places.get(i).size() != numbers) {
                    return false;
                }

            }

            return true;


        }


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

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int numbers = scanner.nextInt();

    }
}



