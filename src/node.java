import java.util.ArrayList;

 class node {


   public ArrayList<ArrayList<card> > places;

    {
        places = new ArrayList<>();
    }

    public boolean expand=false ;
    int depth;
    node father;

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

    public void showTheStates(){


        for (int i = 0; i < places.size(); i++) {

            for (int j = 0; j < places.get(i).size(); j++) {

                System.out.print(places.get(i).get(j));
            }
            System.out.println();
        }

        System.out.println("/////////////////////");

    }

}


