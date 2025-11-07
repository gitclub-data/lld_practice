
import java.util.List;
import java.io.FileWriter;
import java.util.ArrayList;

class Documents{
    List<StringBuilder> elements = new ArrayList<>(); // List to hold document // line by line
    // Methods to manipulate document
    void addtext(StringBuilder text){
        elements.add(text);
    }
    void addimage(StringBuilder image){
        StringBuilder imagetext = new StringBuilder("[Image: " + image + " ]");
        elements.add(imagetext);
    }
    private String renderer(){
        StringBuilder renderedContent = new StringBuilder();
        for(StringBuilder element : elements){
            renderedContent.append(element).append("\n");
        }
        return renderedContent.toString();
    }
    void save(){
        String content = renderer();
        System.out.println("Saving document with content: " + content);
        try{
            FileWriter writer = new FileWriter("document.txt");
            writer.write(content);
            writer.close();
        }
        catch(Exception e){
            System.out.println("An error occurred while saving the document.");
        }
    }
    void changetexttobold(int startindex, int endindex){
        for(int i = startindex; i < Math.min(elements.size(),endindex+1); i++){
            StringBuilder element = elements.get(i);
            element.append("**").append(element).append("**");
            elements.set(i, element);
        }
    }
    void changetexttoitellic(int startindex, int endindex){
        for(int i = startindex; i < Math.min(elements.size(),endindex+1); i++){
            StringBuilder element = elements.get(i);
            element.append("*").append(element).append("*");
            elements.set(i, element);
        }
    }
}


public class DocumentWriter{
    public static void main(String args[]){
        System.out.println("This is a bad design example.");
        Documents doc = new Documents();
        StringBuilder text1 = new StringBuilder("Hello, this is a sample document. ");
        StringBuilder text2 = new StringBuilder("This document contains text and images. ");
        StringBuilder image1 = new StringBuilder("image1.png");
        doc.addtext(text1);
        doc.addtext(text2);
        doc.addimage(image1);
        doc.changetexttobold(0,1);
        doc.save();
    }
}