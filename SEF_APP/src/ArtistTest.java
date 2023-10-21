import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ArtistTest {
    private Artist artist;

    @Before
    public void setUp() {
        artist = new Artist(
        		"869MMMRA_%",               // Artist ID
                "Justin Beiber",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"
            ))
        );
    }

    @Test
    public void testAddArtistSuccess() {
        assertTrue(artist.addArtist());//Testing addArtist function using the data from the setUp() above
    }
    @Test
    public void testAddArtistFailureInvalidID() {//Test case aims to assert False if the ID provided does not match the conditions specified
        Artist artist = new Artist(
            "InvalidID",//Providing Invalid ID 
            "Justin Beiber",
            "Melbourne|Victoria|Australia",
            "05-11-2001",
            "A talented musician with a passion for music and has won many awards for his music.",
            new ArrayList<>(Arrays.asList("Singer", "Songwriter")),
            new ArrayList<>(Arrays.asList("pop", "jazz")),
            new ArrayList<>(Arrays.asList(
                "2022, Best Song Written For Visual Media",
                "2020, Best Song Written For Social Media",
                "2023, Best Song Written for SEF"
            ))
        
        );
        
        assertFalse(artist.addArtist());
        Artist artist1 = new Artist(
                "123",//Providing Invalid ID
                "Justin Beiber",
                "Melbourne|Victoria|Australia",
                "05-11-2001",
                "A talented musician with a passion for music and has won many awards for his music.",
                new ArrayList<>(Arrays.asList("Singer", "Songwriter")),
                new ArrayList<>(Arrays.asList("pop", "jazz")),
                new ArrayList<>(Arrays.asList(
                    "2022, Best Song Written For Visual Media",
                    "2020, Best Song Written For Social Media",
                    "2023, Best Song Written for SEF"
                ))
            
            );
        assertFalse(artist1.addArtist());
        
    }
    @Test
    public void testAddArtistFailureInvalidBio() { //Test case aims to assert False if the Bio provided is Less than 10 or Greater than 30 words
        Artist artist = new Artist(//Provided Bio with less than 10 words
        		"869MMMRA_%",               // Artist ID
                "Justin Beiber",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talen .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"
            ))
        );
        assertFalse(artist.addArtist());
        Artist artist1 = new Artist(//Provided Bio with more than 30 words 
        		"889ABCD#$%",               // Artist ID
                "Leo Das",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music. A talented musician with a passion for music and has won many awards for his music. A talented musician with a passion for music and has won many awards for his music. A talented musician with a passion for music and has won many awards for his music. A talented musician with a passion for music and has won many awards for his music. A talented musician with a passion for music and has won many awards for his music.A talented musician with a passion for music and has won many awards for his music. .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"
            ))
        );
        assertFalse(artist1.addArtist());
    }
    @Test
    public void testAddArtistFailureInvalidOccupations() {// Test to check conditions of Occupation 
        Artist artist = new Artist(//Providing too many occupations 
            "789ABCD#$%",
            "Justin Beiber",
            "Melbourne|Victoria|Australia",
            "05-11-2001",
            "A talented musician with a passion for music and has won many awards for his music.",
            new ArrayList<>(Arrays.asList("Singer", "Songwriter", "Guitarist", "Pianist", "Drummer", "Violinist")), // Too many occupations
            new ArrayList<>(Arrays.asList("pop", "jazz")),
            new ArrayList<>(Arrays.asList(
                "2022, Best Song Written For Visual Media",
                "2020, Best Song Written For Social Media",
                "2023, Best Song Written for SEF"
            ))
        );
        assertFalse(artist.addArtist());//Asserting False
        Artist artist1 = new Artist(//Providing 0 occupations 
                "889ABCD#$%",
                "Leo Das",
                "Chennai|TN|India",
                "05-11-2001",
                "A talented musician with a passion for music and has won many awards for his music.",
                new ArrayList<>(Arrays.asList()), // 0 Occupation
                new ArrayList<>(Arrays.asList("pop", "jazz")),
                new ArrayList<>(Arrays.asList(
                    "2022, Best Song Written For Visual Media",
                    "2020, Best Song Written For Social Media",
                    "2023, Best Song Written for SEF"
                ))
            );
            assertFalse(artist1.addArtist());//Asserting False
    }
    @Test
    public void testAddArtistFailureInvalidGenres() {//Test to check if the Genre Conditions are matched
        Artist artist = new Artist(//Providing more than 5 Genres
            "789ABCD#$%",
            "Justin Beiber",
            "Melbourne|Victoria|Australia",
            "05-11-2001",
            "A talented musician with a passion for music and has won many awards for his music.",
            new ArrayList<>(Arrays.asList("Singer", "Songwriter")),
            new ArrayList<>(Arrays.asList("pop", "jazz", "rock", "rap", "country", "classical")), // Too many genres
            new ArrayList<>(Arrays.asList(
                "2022, Best Song Written For Visual Media",
                "2020, Best Song Written For Social Media",
                "2023, Best Song Written for SEF"
            ))
        );
        assertFalse(artist.addArtist());
        Artist artist1 = new Artist(//Providing 1 Genre
                "889ABCD#$%",
                "Leo Das",
                "Chennai|TN|India",
                "05-11-2001",
                "A talented musician with a passion for music and has won many awards for his music.",
                new ArrayList<>(Arrays.asList("Singer,Songwriter")), 
                new ArrayList<>(Arrays.asList("pop")),//1 Genre
                new ArrayList<>(Arrays.asList(
                    "2022, Best Song Written For Visual Media",
                    "2020, Best Song Written For Social Media",
                    "2023, Best Song Written for SEF"
                ))
            );
            assertFalse(artist1.addArtist());//Asserting False
    }
    @Test
    public void testAddArtistSuccessValidBirthdate() {//Asserting true if the BirthDate is provided in correct format
        Artist artist = new Artist(
        		"869MMMRA_%",               // Artist ID
                "Leo Das",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"
         
            ))
        );
        assertTrue(artist.addArtist());
       Artist artist1 = new Artist(
        		"869MMMRA_%",               // Artist ID
                "Justin Beiber",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "01-12-1990",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"
            ))
        );
       assertTrue(artist1.addArtist());//Test returns True
    }
    @Test
    public void testAddArtistFailureInvalidBirthdate() {//Testing with Invalid Format of Date Birth
        Artist artist = new Artist(
            "789ABCD#$%",
            "Justin Beiber",
            "Melbourne|Victoria|Australia",
            "1899-05-11", // Invalid birthdate 
            "A talented musician with a passion for music and has won many awards for his music.",
            new ArrayList<>(Arrays.asList("Singer", "Songwriter")),
            new ArrayList<>(Arrays.asList("pop", "jazz")),
            new ArrayList<>(Arrays.asList(
                "2022, Best Song Written For Visual Media",
                "2020, Best Song Written For Social Media",
                "2023, Best Song Written for SEF"
            ))
        );
        assertFalse(artist.addArtist());
        Artist artist1 = new Artist(
        		"869MMMRA_%",               // Artist ID
                "Leo Das",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "03",              // Invalid Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"
         
            ))
           
        );
        assertFalse(artist1.addArtist());
        
    }
    @Test
    public void testUpdateArtistSuccess() {//Testing to check if Artist is updating if same ID is present
        // Add the artist first
        Artist artist = new Artist(
        		"969MMMRA_%",               // Artist ID
                "Yun Bei",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"
           
            ))
        );
        assertTrue(artist.addArtist());
        // Update artist information
        assertTrue(artist.updateArtist("969MMMRA_%",               // Artist ID
                "Adam Jr",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"))));
        assertTrue(artist.updateArtist("969MMMRA_%",               // Artist ID
                "Aceu",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"))));
    }//Test Returns True and updates Because there is an existing ID
    @Test
    public void testUpdateArtistFailureNonExistingID() {//Testing to check If update Function works if ID is not present
        Artist artist = new Artist(
            "789ABCD#$%",
            "Justin Beiber",
            "Melbourne|Victoria|Australia",
            "05-11-2001",
            "A talented musician with a passion for music and has won many awards for his music.",
            new ArrayList<>(Arrays.asList("Singer", "Songwriter")),
            new ArrayList<>(Arrays.asList("pop", "jazz")),
            new ArrayList<>(Arrays.asList(
                "2022, Best Song Written For Visual Media",
                "2020, Best Song Written For Social Media",
                "2023, Best Song Written for SEF"
            ))
        );

        // Attempt to update artist information with a non-existing ID
        assertFalse(artist.updateArtist("889ABCD#$$", "Jane Smith", "Los Angeles|CA|USA", "10-05-2005",
            "A versatile musician with a passion for music.",
            new ArrayList<>(Arrays.asList("Singer", "Pianist")),
            new ArrayList<>(Arrays.asList("Pop", "Classical")),
            new ArrayList<>(Arrays.asList("2015, Grammy Award", "2022, Golden Globe Award"))));
        assertFalse(artist.updateArtist("989ABCD#$$", "Jane Smith", "Los Angeles|CA|USA", "10-05-2005",
                "A versatile musician with a passion for music.",
                new ArrayList<>(Arrays.asList("Singer", "Pianist")),
                new ArrayList<>(Arrays.asList("Pop", "Classical")),
                new ArrayList<>(Arrays.asList("2015, Grammy Award", "2022, Golden Globe Award"))));
    }//Returns False 
    @Test
    public void testUpdateArtistFailureInvalidOccupation() {//Test to update occupation for artist born before 2000
        // Add the artist first
        Artist artist = new Artist(
            "789ABCD#$%",
            "Justin Beiber",
            "Melbourne|Victoria|Australia",
            "05-11-1990",
            "A talented musician with a passion for music and has won many awards for his music.",
            new ArrayList<>(Arrays.asList("Singer", "Songwriter")),
            new ArrayList<>(Arrays.asList("pop", "jazz")),
            new ArrayList<>(Arrays.asList(
                "2022, Best Song Written For Visual Media",
                "2020, Best Song Written For Social Media",
                "2023, Best Song Written for SEF"
            ))
        );

        // Attempt to update occupation for an artist born before 2000 (should fail)
        assertFalse(artist.updateArtist("789ABCD#$%",
                "Justin Beiber",
                "Melbourne|Victoria|Australia",
                "05-11-1990",
                "A talented musician with a passion for music and has won many awards for his music.",
                new ArrayList<>(Arrays.asList("Singer", "Composer")),
                new ArrayList<>(Arrays.asList("pop", "jazz")),
                new ArrayList<>(Arrays.asList(
                    "2022, Best Song Written For Visual Media",
                    "2020, Best Song Written For Social Media",
                    "2023, Best Song Written for SEF"))));
        assertFalse(artist.updateArtist("789ABCD#$%",
                "Justin Beiber",
                "Melbourne|Victoria|Australia",
                "05-11-1990",
                "A talented musician with a passion for music and has won many awards for his music.",
                new ArrayList<>(Arrays.asList("Singer", "Composer","SongWriter")),
                new ArrayList<>(Arrays.asList("pop", "jazz")),
                new ArrayList<>(Arrays.asList(
                    "2022, Best Song Written For Visual Media",
                    "2020, Best Song Written For Social Media",
                    "2023, Best Song Written for SEF"))));
    }//Test returns False because the occupation has been changed
    @Test
    public void testUpdateArtistFailureInvalidAwardYear() {//testing to to check if Award year changes for artist born before 2000
        // Add the artist first
        Artist artist = new Artist(
            "789ABCD#$%",
            "Justin Beiber",
            "Melbourne|Victoria|Australia",
            "05-11-1990",
            "A talented musician with a passion for music and has won many awards for his music.",
            new ArrayList<>(Arrays.asList("Singer", "Songwriter")),
            new ArrayList<>(Arrays.asList("pop", "jazz")),
            new ArrayList<>(Arrays.asList(
                "2022, Best Song Written For Visual Media",
                "2020, Best Song Written For Social Media",
                "2023, Best Song Written for SEF"
            ))
        );

        // Attempt to update an award year for an artist born before 2000 (should fail)
        assertFalse(artist.updateArtist("789ABCD#$%", "Jane Smith", "Los Angeles|CA|USA", "05-11-1990",
            "A versatile musician with a passion for music.",
            new ArrayList<>(Arrays.asList("Singer", "Pianist")),
            new ArrayList<>(Arrays.asList("Pop", "Classical")),
            new ArrayList<>(Arrays.asList("1995, Grammy Award", "2022, Golden Globe Award"))));
        assertFalse(artist.updateArtist("789ABCD#$%", "Jane Smith", "Los Angeles|CA|USA", "05-11-1990",
                "A versatile musician with a passion for music.",
                new ArrayList<>(Arrays.asList("Singer", "Pianist")),
                new ArrayList<>(Arrays.asList("Pop", "Classical")),
                new ArrayList<>(Arrays.asList("1995, Grammy Award", "2022, Golden Globe Award"))));
    }
    @Test
    public void testUpdateArtistFailureInvalidGenreChange() {//Testing to check if pop and rock can be in one artist
        // Add the artist first
        Artist artist = new Artist(
        		"869MMMRA_%",               // Artist ID
                "Justin Beiber",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"
            
            ))
        );
        assertTrue(artist.addArtist());

        // Attempt to change artist's genres to include "pop" and "rock" simultaneously (should fail)
        assertFalse(artist.updateArtist(	"869MMMRA_%",               // Artist ID
                "Justin Beiber",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "rock")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"))));
        
        assertFalse(artist.updateArtist(	"869MMMRA_%",               // Artist ID
                "Justin Beiber",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "rock")),           // Artist Genres
                new ArrayList<>(List.of(
                        "2022, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"))));
    }
    @Test
    public void testUpdateArtistFailureInvalidAwardTitleChange() {
        // Add the artist first
        Artist artist = new Artist(
        		"869MMMRA_%",               // Artist ID
                "Justin Beiber",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),           // Artist Genres
                new ArrayList<>(List.of(
                        "1990, Best Song Written For Visual Media",   // Artist Awards
                        "2020, Best Song Written For Social Media",
                        "2023, Best Song Written for SEF"
            ))
        );
        assertTrue(artist.addArtist());

        // Attempt to update an award title for an award given before 2000 (should fail)
        assertFalse(artist.updateArtist("869MMMRA_%",               // Artist ID
                "Justin Beiber",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),
            new ArrayList<>(Arrays.asList("2022, New Award Title",
            		"2020, Best Song Written For Social Media",
                    "2023, Best Song Written for SEF"))));
        
        
     // Attempt to update an award title for an award given before 2000 (should fail)
        assertFalse(artist.updateArtist("869MMMRA_%",               // Artist ID
                "Justin Beiber",                 // Artist Name
                "Melbourne|Victoria|Australia", // Artist Address
                "05-11-2001",              // Artist Birthdate
                "A talented musician with a passion for music and has won many awards for his music .", // Artist Bio
                new ArrayList<>(List.of("Singer", "Songwriter")), // Artist Occupations
                new ArrayList<>(List.of("pop", "jazz")),
            new ArrayList<>(Arrays.asList("2021, New Award Title","2020, Best Song Written For Social Media",
                    "2023, Best Song Written for SEF"))));
    }




      
    }

    

