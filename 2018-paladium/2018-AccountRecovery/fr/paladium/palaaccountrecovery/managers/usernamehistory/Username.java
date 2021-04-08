package fr.paladium.palaaccountrecovery.managers.usernamehistory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Username {
	
	String name;
    long changedToAt;

    public Username(String name, long changedToAt){
        this.name = name;
        this.changedToAt = changedToAt;
    }

    public String getName(){
        return name;
    }

    public String getTime(){
        if(changedToAt == 0){
            return "original";
        }else{
            return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(changedToAt));
        }
    }

}
