package IDS;

import java.util.ArrayList;

class card {

    public char color;
    public int number;

    public card(char color, int number) {
        this.color = color;
        this.number = number;
    }
    public int getNumber() {
        return number;
    }

    public char getColor() {
        return color;
    }



}
class node{

    public boolean initial = false;
    public ArrayList<ArrayList<card>> places;

    {
        places = new ArrayList<>();
    }
    public String action;
    public ArrayList<String> actions = new ArrayList<>();
    public boolean expand=false ;
    int depth;
    node parent;

    public int getDepth(){
        return depth;
    }
    public node getParent(){
        return parent;
    }

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
class ids_solving{
    public ArrayList<node> frontier = new ArrayList<>();
    public ArrayList<node> explored = new ArrayList<>();

}






public class Main {
}
