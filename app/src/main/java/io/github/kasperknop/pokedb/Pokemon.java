package io.github.kasperknop.pokedb;

class Pokemon {

    private int number;
    private String name;
    private String primaryType;
    private String secondaryType;
    String image;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public String getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(String secondaryType) {
        this.secondaryType = secondaryType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Pokemon(int number, String name, String primaryType, String secondaryType, String image) {
        this.number = number;
        this.name = name;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.image = image;
    }
}
