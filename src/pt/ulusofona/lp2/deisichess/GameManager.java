package pt.ulusofona.lp2.deisichess;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class GameManager {

    public GameManager() {
    }



    public boolean loadGame(File file) {

        // tou a aplicar a lógica do projeto passado
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int linha = 0; //para contar o numero de linhas a ajudar a colocar os atributos

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(":");

                if (linha == 0){
                    // assinalar o tamanho
                }else if (linha == 1){
                    // assinalar o numero de peças
                }else {
                    /*
                   if(linhas <= 1+numeroDePecas){
                        tokens[0] = idDaPeca;
                        tokens[1] = tipoDaPeca;
                        tokens[2] = equipa;
                        tokens[3] = alcunhaDaPeca;

                       -> assinalar cada numero separado por : à sua caracteristica de acordo com o regulamento do projeto
                    }else{
                    tokens[0] = quadrado1;
                        tokens[1] = quadrado2;
                        tokens[2] = quadrado3;
                        tokens[3] = quadrado4;

                        -> aqui vemos se os quadrados estão ocupados e obtemos as coordenadas sendo quadrado(n) e n sendo a ordenada=y e (linhas - (1+numeroDePecas)) a abcissa=x
                    }

                     */
                }

                linha++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

   public int getBoardSize() {
        return 0;
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        return false;
    }

    public String [] getSquareInfo(int x, int y) {
        return null;
    }

    public String [] getPieceInfo(int ID) {
        return null;
    }


    public String getPieceInfoAsString(int ID) {
        return null;
    }

    public int getCurrentTeamID() {
        return 0;
    }

    public boolean gameOver() {
        return false;
    }

    public ArrayList<String> getGameResults() {
        return null;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }
}
