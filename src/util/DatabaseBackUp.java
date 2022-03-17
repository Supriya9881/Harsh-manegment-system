package util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseBackUp {
	public boolean backup() {
		String dbName="pearlclean";
		String dbUserName= "root";
		String dbPassword= "root";
		String pattern = "dd-MM-yyyy";
		String dateInString =new SimpleDateFormat(pattern).format(new Date());
		
		File target = new File("E:/PercleanDatabaseBackUp");
		if (!target.exists()) {
			target.mkdir();
		}
		
		String path= target.getAbsolutePath()+"/PerCleanDatabase-"+dateInString+".sql";
		String executeCmd = "C:/Program Files (x86)/MySQL/MySQL Server 5.5/bin/mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path;
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
                return true;
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
	}
}
