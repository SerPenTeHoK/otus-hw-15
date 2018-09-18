package ru.sergey_gusarov.hw15.domain.books;

import lombok.Data;

@Data
public class Genre {
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + "'" +
                '}';
    }
}
