package domain.catalog.command;

import domain.generic.Command;

public class AddMovieCommand extends Command {
    private String catalogId;
    private String movieId;
    private String name;
    private String year;
    private String duration;
    private String description;
    private String gender;
    private String path;

    public AddMovieCommand(String catalogId, String movieId, String name, String year, String duration, String description, String gender, String path) {
        this.catalogId = catalogId;
        this.movieId = movieId;
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.description = description;
        this.gender = gender;
        this.path = path;
    }

    public AddMovieCommand() {
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
