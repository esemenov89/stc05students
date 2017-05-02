package main.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by STC-05 Team [Aleksei Lysov] on 26.04.2017.
 */
public class StudentServiceUtilImpl implements StudentServiceUtil{
    private static final Logger LOGGER = Logger.getLogger(StudentServiceUtilImpl.class);
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    @Override
    public synchronized void add(String msg){
        list.add(msg);
        checkList();
    }

    private synchronized void checkList(){
        if (list.size() >= 5){
            List<String> writeList = new ArrayList<>(list);
            list = new CopyOnWriteArrayList<>();

            for (String s : writeList) {
                try {
                    Files.write(Paths.get("c:/main/projects/stc05students/LOG.log"), (s + "\r\n").getBytes(), StandardOpenOption.APPEND);
                    LOGGER.info(s);
                }catch (IOException e) {
                    LOGGER.warn(e);
                }
            }
        }
    }
}
