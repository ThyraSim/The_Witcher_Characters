package com.example.thewitcher;

import java.util.Objects;

public class AccountPerso {
    private String titre;
    private String description;
    private int image;

    // Constructor
    public AccountPerso(String titre, String description, int image) {
        this.titre = titre;
        this.description = description;
        this.image = image;
    }

    // Getter methods
    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    // Setter methods
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(int image) {
        this.image = image;
    }

    // Override equals and hashCode (similar to data class behavior in Kotlin)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountPerso aPerso = (AccountPerso) o;
        return image == aPerso.image &&
                Objects.equals(titre, aPerso.titre) &&
                Objects.equals(description, aPerso.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre, description, image);
    }

    // toString method (similar to data class behavior in Kotlin)
    @Override
    public String toString() {
        return "Post{" +
                "titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }
}
