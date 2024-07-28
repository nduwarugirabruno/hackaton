package io.sambell.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@SpringBootTest
class ProductApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    void formatMemberNumber() {
        String nom = "NDUWARUGIRA MAMA";
        String prenom = "BRUNO Links";

        Date date = new Date();

        LocalDate dateCreation = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime timeCreation = date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

        // Formater la date avec des zéros en préfixe si nécessaire
        String formattedDate = String.format("%1$tY%1$tm%1$td", dateCreation);
        String formattedTime = String.format("%1$tH%1$tM%1$tS", timeCreation);

        String userName = nom.split(" ")[0] + prenom.split(" ")[0];
        userName = userName.length() < 16 ? String.format("%-16s", userName).replace(' ', '0') : userName.substring(0, 16);
        String dateTime = formattedDate+formattedTime;

        // Créer l'identifiant unique avec le format spécifié
        String identifiant = userName+dateTime;

        // Assurer que l'identifiant a exactement 20 caractères
        if (identifiant.length() > 30) {
            identifiant = identifiant.substring(0, 30);
        } else if (identifiant.length() < 30) {
            identifiant = String.format("%1$-30s", identifiant);
        }

        System.out.println("Identifiant unique : " + identifiant.toUpperCase());
    }
}
