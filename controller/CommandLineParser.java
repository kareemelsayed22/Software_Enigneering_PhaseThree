
package controller;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class CommandLineParser {
    
    public static Map<String,String> parseFflags(String[]args){
        Map<String,String> flags = new HashMap<String,String>();
        for(int i = 0; i < args.length; i++){
            String arg = args[i];
            if(arg.equals("-p")){
                flags.put(arg, arg);                
            }
            else if(arg.startsWith("-") && i < (args.length - 1)){
                flags.put(arg,args[i+1]);
                i++;
            }
        }
        return flags;
    }
    
}
