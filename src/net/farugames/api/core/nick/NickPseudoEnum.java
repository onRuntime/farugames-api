package net.farugames.api.core.nick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by SweetKebab_ on 2018-07-26
 */
public class NickPseudoEnum {
    public static String getRandomPseudo() {
        List<String> pseudoList = new ArrayList<String>();
        pseudoList.add("kendallsthegreat");
        pseudoList.add("wclfie");
        pseudoList.add("M2RTEGU3_");
        pseudoList.add("SpeedyTBNRgirl");
        pseudoList.add("Tiah_MiilyZ");
        pseudoList.add("Swaths");
        pseudoList.add("ambcr");
        pseudoList.add("Luccifina");
        pseudoList.add("travis_bageldog");
        pseudoList.add("ImJxshiiii");
        pseudoList.add("TheEddies");
        pseudoList.add("RhuanCabral");
        pseudoList.add("zPsychic_");
        pseudoList.add("BimNebengefallt");
        pseudoList.add("KleinesLebewesen");
        pseudoList.add("Sumiya");
        pseudoList.add("Enzothesabercat");
        pseudoList.add("DragonGirl_Ita");
        pseudoList.add("Hawkgirl__");
        pseudoList.add("YonueGamerYT");
        pseudoList.add("Nerminka");
        pseudoList.add("Ausdrvcksloser");
        pseudoList.add("_ErenBuluntu_");
        pseudoList.add("snootie");
        pseudoList.add("SukoDoragon");
        pseudoList.add("_LegitMattyPvP");
        pseudoList.add("Purplu");
        pseudoList.add("distastrophe");
        pseudoList.add("IAmMyselfBae");

        return pseudoList.get(new Random().nextInt(pseudoList.size()));
    }
}
