import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Artist {


    private String ID;
    private String Name;
    private String Address;
    private String Birthdate;
    private String Bio;
    private ArrayList<String> Occupations;
    private ArrayList<String> Genres;
    private ArrayList<String> Awards;

    public Artist(String id, String name, String address, String birthdate, String bio, ArrayList<String> occupations, ArrayList<String> genres, ArrayList<String> awards) {
        ID = id;
        Name = name;
        Address = address;
        Birthdate = birthdate;
        Bio = bio;
        Occupations = occupations;
        Genres = genres;
        Awards = awards;
    }
    public boolean addArtist() {
        if (checkArtistID(ID) && checkBirthdate(Birthdate) && checkAddress(Address) &&
                checkBio(Bio) && checkOccupations(Occupations) && checkGenres(Genres) &&
                checkAwards(Awards)) {
            if (addToTxtFile()) {
                return true;
            }
        }

        return false;
    }
        private boolean checkArtistID(String id) {
            // Condition 1: Artist ID should be exactly 10 characters long
            if (ID.length() != 10) {
                return false;
            }

            // The first three characters should be numbers between 5 to 9
            String numbersPart = id.substring(0, 3);
            if (!Pattern.matches("[5-9]{3}", numbersPart)) {
                return false;
            }

            // The characters 4th to 8th should be upper case letters (A - Z)
            String lettersPart = id.substring(3, 8);
            if (!Pattern.matches("[A-Z]{5}", lettersPart)) {
                return false;
            }

            // The last two characters should be a special character
            String specialPart = id.substring(8);
            if (!Pattern.matches("[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?\\\\|]+", specialPart)) {
                return false;
            }

            return true;
        }

        private boolean checkBirthdate(String birthdate) {
            // Condition 2: Birthdate should follow the format DD-MM-YYYY
            return Pattern.matches("\\d{2}-\\d{2}-\\d{4}", birthdate);
        }

        private boolean checkAddress(String address) {
            // Condition 3: Address should follow the format City|State|Country
            return Pattern.matches("[a-zA-Z]+\\|[a-zA-Z]+\\|[a-zA-Z]+", address);
        }

        private boolean checkBio(String bio) {
            // Condition 4: Bio should be between 10 to 30 words
            String[] words = bio.split("\\s+");
            return words.length >= 10 && words.length <= 30;
        }

        private boolean checkOccupations(ArrayList<String> occupations) {
            // Condition 5: An artist should have 1 to 5 occupations
            return occupations.size() >= 1 && occupations.size() <= 5;
        }

        private boolean checkGenres(ArrayList<String> genres) {
            // Condition 6: An artist should be active in 2 to 5 genres
            if (genres.size() < 2 || genres.size() > 5) {
                return false;
            }

            // Condition 7: Artists cannot be active in 'pop' and 'rock' genres at the same time
            return !(genres.contains("pop") && genres.contains("rock"));
        }

        private boolean checkAwards(ArrayList<String> awards) {

            // Condition 6: An artist can have 0 to 3 awards
            if (awards.size() > 3) {
                return false;
            }

            for (String award : awards) {
                String[] parts = award.split(", ");
                if (parts.length != 2) {
                    return false;
                }
                // Check the title length
                String title = parts[1];
                String[]words=title.split(" ");
                int wordCount=words.length;
                if (wordCount<4 || wordCount>10) {
                    return false;
                }
            }

            return true;
    }
    public boolean updateArtist(String newID, String newName, String newAddress, String newBirthdate, String newBio,
                                ArrayList<String> newOccupations, ArrayList<String> newGenres, ArrayList<String> newAwards) {
        // Check if the artist with the specified ID exists in the database
        if (artistExists(ID)) {
            if (checkArtistID(newID) && checkBirthdate(newBirthdate) && checkAddress(newAddress)
                    && checkBio(newBio) && checkOccupations(newOccupations) && checkGenres(newGenres)
                    && checkAwards(newAwards) && canChangeOccupation(newBirthdate, newOccupations)
                    && canChangeAwards(newAwards)) {
                return updateInTxtFile(newID, newName, newAddress, newBirthdate, newBio, newOccupations, newGenres, newAwards);
            }
        }

        return false;
    }
    private boolean canChangeOccupation(String newBirthdate, ArrayList<String> newOccupations) {
        // Condition 2: If an artist was born before 2000, their occupation cannot be changed.
        String[] birthdateParts = newBirthdate.split("-");
        int birthYear = Integer.parseInt(birthdateParts[2]);
        if (birthYear < 2000) {
            // Check if the new occupations are the same as the old ones
            return Occupations.equals(newOccupations);
        }
        return true; // Occupation can be changed if born in or after 2000
    }

    private boolean canChangeAwards(ArrayList<String> newAwards) {
        for (String award : newAwards) {
            String[] parts = award.split(", ");
            if (parts.length != 2) {
                return false;
            }

            String awardYear = parts[0];
            String title = parts[1];

            int year = Integer.parseInt(awardYear);

            // Condition 3: Awards that were given to an artist before 2000 can not be changed (neither their year nor their title).
            if (year < 2000) {
                for (String oldAward : Awards) {
                    String[] oldParts = oldAward.split(", ");
                    int oldYear = Integer.parseInt(oldParts[0]);

                    if (oldYear == year) {
                        return title.equals(oldParts[1]);
                    }
                }
            }

            // Only the title of awards given to an artist after 2000 can be changed.
            // Check if the award exists in the artist's awards list
            if (!Awards.contains(award)) {
                return false;
            }
        }
        return true;
    }

    // Helper method to check if an artist with a given ID exists in the database
    private boolean artistExists(String artistID) {
        String fileName = "artists.txt";

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("ID: " + artistID)) {
                    // The artist with the specified ID exists
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // The artist with the specified ID does not exist
        return false;
    }


        private boolean addToTxtFile() {
            String fileName = "artists.txt";

            try {
                FileWriter writer = new FileWriter(fileName, true);

                // Write the artist information to the file
                writer.write("ID: " + ID + "\n");
                writer.write("Name: " + Name + "\n");
                writer.write("Address: " + Address + "\n");
                writer.write("Birthdate: " + Birthdate + "\n");
                writer.write("Bio: " + Bio + "\n");
                writer.write("Occupations: " + String.join(", ", Occupations) + "\n");
                writer.write("Genres: " + String.join(", ", Genres) + "\n");
                writer.write("Awards: " + String.join(", ", Awards) + "\n");
                writer.write("\n");

                writer.close();

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        private boolean updateInTxtFile(String newID, String newName, String newAddress, String newBirthdate, String newBio,
                ArrayList<String> newOccupations, ArrayList<String> newGenres, ArrayList<String> newAwards) {
            // Create an artist information string with the updated data
            String artistInfo = "ID: " + newID + "\n" +
                    "Name: " + newName + "\n" +
                    "Address: " + newAddress + "\n" +
                    "Birthdate: " + newBirthdate + "\n" +
                    "Bio: " + newBio + "\n" +
                    "Occupations: " + String.join(", ", newOccupations) + "\n" +
                    "Genres: " + String.join(", ", newGenres) + "\n" +
                    "Awards: " + String.join(", ", newAwards) + "\n\n";

            String fileName = "artists.txt";

            try {
                // Read the entire file
                String fileContents = "";
                Scanner scanner = new Scanner(new File(fileName));
                while (scanner.hasNextLine()) {
                    fileContents += scanner.nextLine() + "\n";
                }
                scanner.close();

                // Replace the old artist information with the updated information
                fileContents = fileContents.replaceAll("(?s)ID: " + ID + "\\n.*?\\n\\n", artistInfo);

                // Write the updated contents back to the file
                FileWriter writer = new FileWriter(fileName);
                writer.write(fileContents);
                writer.close();

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }
    }
















