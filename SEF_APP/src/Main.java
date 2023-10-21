import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Artist artist = new Artist(
        		"589MMMRA_%",               // Artist ID
                "Justin Beiber",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "1990, Best Song Written for SEF"
                       
                ))
        );

        // Call the addArtist method to add the artist
        boolean isArtistAdded = artist.addArtist();

        if (isArtistAdded) {
            System.out.println("Artist added successfully!");
        } else {
            System.out.println("Failed to add the artist. Please check the artist's information.");
        }
        if (artist.updateArtist("589MMMRA_%", "Jane Smith", "Melbourne|Victoria|Australia", "05-11-1990",
                "A talented musician with a passion for music and has won many awards for his music .",
                new ArrayList<>(Arrays.asList("Singer", "Songwriter")),
                new ArrayList<>(Arrays.asList("pop", "jazz")),
                new ArrayList<>(Arrays.asList("2022, Best Song Written For Visual Media", "2020, Best Song Written For Social Media","1992, Best Song Written for SEF")))) {
            System.out.println("Artist information updated successfully.");
        } else {
            System.out.println("Failed to update artist information.");
        }
        if (artist.updateArtist("XYZ123@ABC#LM", "New Artist", "Chicago|IL|USA", "20-07-2000",
                "A new artist with a passion for music.",
                new ArrayList<>(List.of("Guitarist")),
                new ArrayList<>(List.of("Rock")),
                new ArrayList<>(List.of("2018, MTV Music Award")))){
            System.out.println("Artist information updated successfully.");
        } else {
            System.out.println("Failed to update artist information. Artist not found.");
        }


        }


    }





