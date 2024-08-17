package com.example.peliculan.Adapters;
import java.util.List;
public class ListFilm {
    private List<Film> data;

    public ListFilm(List<Film> data) {
        this.data = data;
    }

    public List<Film> getData() {
        return data;
    }

    public void setData(List<Film> data) {
        this.data = data;
    }
}
