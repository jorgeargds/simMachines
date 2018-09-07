package com.simmachines.utilities;

import com.simmachines.entities.UserEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataUtility {

    private Function<String, UserEntity> mapToItem = (line) -> {
        String[] p = line.split(",");// a CSV has comma separated lines

        String[] atts = p[0].split("\t");
        atts = this.removeFirstAndLastLetter(atts);

        return createUser(atts);
    };

    public List<UserEntity> getUsersFromInputFile() {
        List<UserEntity> inputList = new ArrayList<UserEntity>();
        try {
            File inputF = new File("src/main/resources/artistToGaguiel.csv");
            // System.out.println(inputF.getAbsoluteFile());
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (FileNotFoundException fileEx) {
            System.out.print(fileEx);
        } catch (IOException ioEx) {
            System.out.print(ioEx);
        }
        return inputList;
    }

    private UserEntity createUser(String[] atts) {
        UserEntity user = new UserEntity(atts[0]);
        for (int i = 1; i < atts.length; i++)
            if (i == 1) {
                user.setBands(new ArrayList<String>(Arrays.asList((atts[1].split(";")))));
            } else {
                user.setFrequencies(Arrays.asList((atts[2].split(";"))).stream()
                .map(
                        s -> Integer.parseInt(s)
                )
                .collect(Collectors.toList()));
            }
        return user;
    }

    private String[] removeFirstAndLastLetter(String[] atts) {
        String [] newAtts = new String[atts.length];
        for (int i = 0; i < atts.length;i++) {
            newAtts[i] = atts[i].substring(1, atts[i].length() - 1);
        }
        return newAtts;
    }

}
