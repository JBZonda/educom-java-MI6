package nu.educom.MI6;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    View view;
    List<Integer> blacklistedAgents = new ArrayList<Integer>();
    List<Integer> loggedInAgents = new ArrayList<Integer>();
    public Model(View view){
        this.view = view;
    }
}
