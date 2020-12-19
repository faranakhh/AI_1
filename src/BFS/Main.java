package BFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

 class card {
    public char color;
    public int number;
    public card(char color, int number) {
        this.color = color;
        this.number = number;
    }
}
//////////////////////////////////////////////////
class node {
   public boolean selected = false;
    public ArrayList<ArrayList<card>> places;
    {
        places = new ArrayList<>();
    }
    public String action;
    public ArrayList<String> actions = new ArrayList<>();
    public boolean expand=false ;
    int depth;
    node parent;
    public ArrayList getAPalace(int number){
        return places.get(number);
    }
    //return the cards in a special place

    public ArrayList getNumberOfAPlace(int number){
        ArrayList<card> arrayListOfCads = places.get(number);//get the cards in a place
        ArrayList<Integer> numbersOfCards = new ArrayList<>();

        for (int i =0 ; i<= arrayListOfCads.size();i++){
            numbersOfCards.add(arrayListOfCads.get(i).number);
        }
        return numbersOfCards;
    }

    public ArrayList getColorOfAplace(int number){
        ArrayList<card> arrayListOfCads = places.get(number);//get the cards in a place
        ArrayList<Character> colorOfCards = new ArrayList<>();

        for (int i =0 ; i<= arrayListOfCads.size();i++){
            colorOfCards.add(arrayListOfCads.get(i).color);
        }
        return colorOfCards;
    }
    public void showThePlaces(){
        for (int i = 0; i < places.size(); i++) {
            for (int j = 0; j < places.get(i).size(); j++) {
                System.out.print(places.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println("//////////////////////////");
    }
    public void setPlace(ArrayList<ArrayList<card>> place) {
        places = place;
    }
}

////////////////////////////////
class bfs_solving {
    public ArrayList<node> frontier = new ArrayList<>();
    public ArrayList<node> explored = new ArrayList<>();
    public boolean expanding(node node, int numbers) {
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

                    if (space1.size() != 0 && space2.size() == 0  ) {
                        card lastCard1 = (card) space1.get(space1.size() - 1);
                        copyOfNode.get(i).remove(lastCard1);
                        copyOfNode.get(j).add(lastCard1);
                        node newNode = new node();
                        newNode.setPlace(copyOfNode);//copy of above node is now new node. setPlace is a function in node class
                        String action = lastCard1.number + "" + lastCard1.color + " moves from " + (i+1) + " to " + (j+1) ;
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
                        if (isGoal(newNode, numbers)) {
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
                    if (space2.size() != 0 && space1.size() != 0) {
                        card lastCard1 = (card) space1.get(space1.size() - 1);
                        card lastCard2 = (card) space2.get(space2.size() - 1);
                        if (lastCard1.number < lastCard2.number) {
                            copyOfNode.get(i).remove(lastCard1);
                            copyOfNode.get(j).add(lastCard1);
                            node newNode = new node();
                            newNode.setPlace(copyOfNode);
                            String nodeAction = lastCard1.number + "" + lastCard1.color + " moves from " + (i+1)  + " to " + (j+1);
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
                                System.out.println("number of actions:" + newNode.actions.size());
                                for (int p = 0; p < newNode.actions.size(); p++) {

                                    System.out.println(newNode.actions.get(p));
                                    System.out.println("number of created nodes:" + frontier.size() + explored.size());
                                    System.out.println("Frontier size : " + frontier.size());
                                    System.out.println("Expanded nodes: " + explored.size());
                                    return true;
                                }
                            } else {
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

    public boolean isGoal(node node, int numbers) {
        char color;
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

            for (int j = 0; j < frontier.get(i).places.size(); j++) {
                if ((!frontier.get(i).getNumberOfAPlace(i).equals(node.getNumberOfAPlace(i))) || (!node.getColorOfAplace(i).equals(node.getColorOfAplace(i)))) {
                    return false;
                } else return true;
            }
        }
        for (int i = 0; i < explored.size(); i++) {
            for (int j = 0; i < explored.get(i).places.size(); j++) {

                if ((!explored.get(i).getNumberOfAPlace(i).equals(node.getNumberOfAPlace(i))) || (!node.getColorOfAplace(i).equals(node.getColorOfAplace(i)))) {
                    return false;
                } else return true;

            }
        }
        return false;
    }

    public void my_bfs(node node, int numbers) {
        boolean temp = false;
        boolean finish = false;
        while (!finish) {
            node nodeForExpanding = frontier.get(0);
            frontier.remove(0);
            explored.add(nodeForExpanding);
            temp = expanding(nodeForExpanding, numbers);
            if (temp == true) {
                finish = true;
                System.out.println("finish");
            }
        }
    }

}

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        int numbersOfRows = scanner.nextInt();
        int numbersOfColors = scanner.nextInt();
        int numberOfCards = scanner.nextInt();
        ArrayList primaryInput;
        {
            primaryInput =new ArrayList();}
        bfs_solving solving = new bfs_solving();
        node firstNode = new node();
        firstNode.depth = 0;
        for (int i = 0; i <= numbersOfRows; i++) {
            String input = scanner.nextLine();
            primaryInput.add(input);
        }
        primaryInput.remove(0);
        ArrayList<ArrayList<card>> places ;{
            places =new ArrayList<>();}
        for (int m = 0; m < numbersOfRows; m++) {
            ArrayList<card> place = new ArrayList();
            String string = (String) primaryInput.get(m);
            String[] ready = string.split("\\s+");
            for (int n = 0; n < ready.length; n++) {
                String aCard = ready[n];
                if (aCard!="#") {
                    int number = Integer.parseInt(String.valueOf(aCard.charAt(0)));
                    char color = aCard.charAt(1);
                    card card = new card(color, number);
                    place.add(card);
                }
            }
            places.add(place);
        }
        firstNode.places = places;
        firstNode.selected = true;
        solving.frontier.add(firstNode);
        solving.my_bfs(firstNode, numberOfCards);

    }

}
