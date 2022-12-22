package com.company;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static boolean siElementDansBF(ArrayList<String> BF, String element)//verifie si un element est dans la base des faits
    {
        boolean existe = false;//si l'element existe dans la bf
        for (int i = 0; i < BF.size(); i++)
        {
            if (element == BF.get(i))
            {
                existe = true;
            }
        }
        return existe;
    }

     public static boolean siDesElementsDansBF(Regle regle,ArrayList<String> BF)//verifie si les elements gauche d'une regle sont dans la base des faits
     {
        boolean existent ;//si plusieurs elements existent dans la bf
        int i=0;
        do
        {
            existent = siElementDansBF (BF,regle.Gauche.get(i));
            i++;

        }while ( existent == true && i < regle.Gauche.size());

        return existent;
     }

    public static void main(String[] args)
    {
        int taille_base_des_faits , nbr_regle , nbr_gauches , nbr_droites ; // la nomination est claire...
        int i , j ; // variables d'indexation
        Regle temp = new Regle() ; //regle intermediaire poiur le remplissage de la base des regles
        boolean maj = false;//variable de verification si la bf a change (mis a jour)
        ArrayList<String> base_des_faits=new ArrayList<>();
        ArrayList<Regle> base_des_regles=new ArrayList<>();

        Scanner Scan = new Scanner(System.in);



        //remplissage de la base des faits
        System.out.println("Combien d'elements y a t'il dans la base des faits ? : ");
        taille_base_des_faits = Scan.nextInt();
        Scan.nextLine();

        for ( i = 0 ; i < taille_base_des_faits ; i++ )//boucle de remplissage de la bf
        {
            System.out.println("Donnez l'elements de la base des faits numero : "+(i+1)+" : ");
            base_des_faits.add(Scan.nextLine());
        }


        //remplissage des regles
        System.out.println("Nombre de regles : ");
        nbr_regle = Scan.nextInt();

        for( i = 0 ; i < nbr_regle ; i++)//boucle de remplissage des regles
        {
            temp = null;

            System.out.println("<--------regle : " +(i+1)+"-------->");

            System.out.println("Nombre de gauches dans la regle : " +(i+1)+" : ");
            nbr_gauches = Scan.nextInt();

            System.out.println("Nombre de droites dans la regle : " +(i+1)+" : ");
            nbr_droites = Scan.nextInt();

            for(j=0;j<nbr_gauches;j++) //remplissage de la partie gauche d'une regle
            {
                System.out.println("Regle "+(i+1)+" Gauche "+(j+1)+" : ");
                temp.Gauche.set(j ,Scan.nextLine() );
            }

            for(j=0;j<nbr_droites;j++)//remplissage de la partie droite d'une regle
            {
                System.out.println("Regle "+(i+1)+" Droite "+(j+1)+" : ");
                temp.Droite.set(j ,Scan.nextLine() );
            }

            base_des_regles.add(temp); //ajoute de la derniere regles input dans la bf

        }

        String but;
        System.out.println("Donnez le but : ");
        but = Scan.nextLine();

        //traitment chainage avant
        do
        {
            for (i = 0; i < base_des_regles.size(); i++)//boucle de traintemet de tt les regles
            {
                if (siDesElementsDansBF(base_des_regles.get(i), base_des_faits))//si tt les gauches d'une regles sont dans le bf
                {
                    maj = true; // une maj a ete faite a la bf
                    for (j = 0; j < base_des_regles.get(i).Droite.size(); j++) //ajout de tt les droites d'une regles dans la bf
                        base_des_faits.add(base_des_regles.get(i).Droite.get(j));
                }
                else
                    maj = false ; // aucune maj a ete faite
            }
        }while (!(siElementDansBF(base_des_faits, but)) && maj == true);//le but n'est pas dans la bf
                                                                        // et une maj a ete faite a la bf pour continiuer la chainage

        //verification si le but est dans la bf
        if ( siElementDansBF(   base_des_faits,but ) )
            System.out.println("Reussite");
        else
            System.out.println("Fail");
    }
}