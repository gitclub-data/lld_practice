import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

interface Element {
    String render();
    String getElement();
}

class TextElement implements Element {
    private String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public String render() {
        return "Text: " + text;
    }

    @Override
    public String getElement() {
        return text;
    }

}

class ImageElement implements Element {
    private String imageUrl;

    public ImageElement(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String render() {
        return "Image URL: [ " + imageUrl + " ]";
    }

    @Override
    public String getElement() {
        return imageUrl;
    }

}

interface DocumentWithText {
    void addText(String text);
}

interface DocumentWithImage {
    void addImage(String imageurl);
}

interface DocumentWithTextAndImage extends DocumentWithText, DocumentWithImage {
    List<Element> getElements();
}

class WordDocument {
    private List<Element> elements;

    public WordDocument() {
        this.elements = new ArrayList<>();
    }

    public void addText(String text) {
        elements.add(new TextElement(text));
    }

    public void addImage(String imageurl) {
        elements.add(new ImageElement(imageurl));
    }

    public List<Element> getElements() {
        return elements;
    }  
}


interface Renderer {
    String render(List<Element> elements);
}

class TextRenderer implements Renderer {
    @Override
    public String render(List<Element> elements) {
        StringBuilder sb = new StringBuilder();
        for (Element element : elements) {
            sb.append(element.render()).append("\n");
        }
        return sb.toString();
    }
}


interface Persistance {
    void save(String content, String name);
}

class SaveAsTextFile implements Persistance {
    @Override
    public void save(String content, String name) {
        try (FileWriter writer = new FileWriter(name)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}

interface Formatter {
    Element format(Element Element);
}

interface TextFormatter extends Formatter{
}

class BoldTextFormatter implements TextFormatter{
    @Override
    public Element format(Element element){
        if(element instanceof TextElement){
            String originalText = ((TextElement) element).getElement();
            String boldText = "**" + originalText + "**";
            return new TextElement(boldText);
        }
        return element;
    }    
}

class ItalicTextFormatter implements TextFormatter{
    @Override
    public Element format(Element element){
        if(element instanceof TextElement){
            String originalText = ((TextElement) element).getElement();
            String italicText = "*" + originalText + "*";
            return new TextElement(italicText);
        }
        return element;
    }
}

public class DocumentWriter{
    public static void main(String[] args){
        
        WordDocument document = new WordDocument();
        document.addText("Hello, World!");
        document.addImage("http://example.com/image.png");
        document.addText("This is a sample document.");

        // Apply formatting
        TextFormatter boldFormatter = new BoldTextFormatter();
        TextFormatter italicFormatter = new ItalicTextFormatter();

        List<Element> formattedElements = new ArrayList<>();
        for (Element element : document.getElements()) {
            Element formattedElement = boldFormatter.format(element);
            formattedElement = italicFormatter.format(formattedElement);
            formattedElements.add(formattedElement);
        }

        // Render document
        Renderer renderer = new TextRenderer();
        String renderedContent = renderer.render(formattedElements);

        // Save document
        Persistance persistance = new SaveAsTextFile();
        persistance.save(renderedContent, "document.txt");

    }
}

