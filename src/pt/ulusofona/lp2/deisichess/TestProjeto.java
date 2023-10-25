package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestProjeto {

   @Test
   public void teste_loadGame_1() {

       GameManager gm = new GameManager();

       assertTrue(gm.loadGame(new File("4x4.txt")));
   }



}
