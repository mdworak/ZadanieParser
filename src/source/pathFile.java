package source;

import org.w3c.dom.Element;
import parser.parserFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class pathFile implements fileSource {
    @Override
    public void getFile(String path, String mainTag, parserFile parserFile) throws IOException {
        String startTag = buildTag(1,mainTag);
        String endTag = buildTag(2,mainTag);
        boolean isEndTag=false;
        boolean isStartTag=false;
        boolean canAdd=false;
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textLine = bufferedReader.readLine();
        List<String> nodeList = new ArrayList<String>();
        do {
            isStartTag=textLine.toLowerCase().contains(startTag.toLowerCase());
            isEndTag=textLine.toLowerCase().contains(endTag.toLowerCase());
            if(isEndTag) {
                parserFile.parseFile(nodeList);
                canAdd=false;
                nodeList=new ArrayList<String>();
            }
            if(canAdd)
                nodeList.add(textLine.trim());
            if(isStartTag)
                canAdd=true;
            textLine = bufferedReader.readLine();

        } while(textLine != null);

        bufferedReader.close();
    }

    public static String buildTag(Integer mode, String mainTag){ //1-startTag, 2-endTag
        StringBuilder startTagBuilder = new StringBuilder();
        startTagBuilder.append((mode==1)?"<":"</");
        startTagBuilder.append(mainTag);
        startTagBuilder.append(">");
        return startTagBuilder.toString();
    }

}
