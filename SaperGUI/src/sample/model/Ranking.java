package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ranking {
    public List<String> nameList = new ArrayList<String>();
    public List<Integer> scoreList = new ArrayList<Integer>();
    public List<String> rankingList = new ArrayList<String>();


    public Ranking() throws IOException {
        File plik = new File("C:\\Users\\Bill\\Desktop\\SaperGUI\\src\\sample\\model\\ranking.txt");
        Scanner odczyt = new Scanner(plik);

        for(int i=0; i<(countLines(plik)/2);i++){
            String name = odczyt.nextLine();
            nameList.add(name);
            String score = odczyt.nextLine();
            scoreList.add(Integer.parseInt(score));
            String zdanie = name+" - "+score+" sec";
            rankingList.add(zdanie);
        }

        odczyt.close();
    }

    public int countLines(File plik) throws IOException {
        FileReader fr=new FileReader(plik);  //Creation of File Reader object
        BufferedReader br = new BufferedReader(fr);
        int lines=0;
        while(br.readLine() != null){
            lines++;
        }

        fr.close();
        br.close();
        return lines;
    }


    public void addToRanking(String wynik, String nazwa) throws IOException {
        Integer wynikk = Integer.parseInt(wynik);
        String zdanie;

        for(int i=0;i< rankingList.size();i++){
            nameList.set(i,nameList.get(i));
            scoreList.set(i,scoreList.get(i));
            zdanie = nameList.get(i)+" - "+scoreList.get(i)+" sec";
            rankingList.set(i,zdanie);
        }

            nameList.add(nazwa);
            scoreList.add(wynikk);
            zdanie = nazwa+" - "+wynikk+" sec";
            rankingList.add(zdanie);

        File plik = new File("C:\\Users\\Bill\\Desktop\\SaperGUI\\src\\sample\\model\\ranking.txt");
        PrintWriter zapis = new PrintWriter(plik);

        for(int i=0;i<rankingList.size();i++){
            zapis.println(nameList.get(i));
            zapis.println(scoreList.get(i));
        }
        zapis.close();
    }
}
