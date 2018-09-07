package com.simmachines.entities;


import java.util.List;

public class UserEntity {

    private String id;
    private List<String> bands;
    private List<Integer> frequencies;

    public UserEntity(String id) {
        this.id = id;
    }

    public UserEntity(String id, List<String> bands,List<Integer> frequencies) {
        this.id = id;
        this.bands = bands;
        this.frequencies = frequencies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getBands() {
        return bands;
    }

    public void setBands(List<String> bands) {
        this.bands = bands;
    }

    public List<Integer> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(List<Integer> frequencies) {
        this.frequencies = frequencies;
    }

    @Override
    public String toString() {
        String state = "";
        state += "ID:" + this.getId() + "\n";

        return state;
    }
}
