from typing import Protocol as protocols

class Element(protocols):
    def render(self) -> str : pass
    def getElements(self) -> str : pass

class TextElement(Element):
    def __init__(self, text: str):
        self.text = text

    def render(self) -> str:
        # do some modifications if needed
        return self.text

    def getElements(self) -> str:
        return self.text

class ImageElement(Element):
    def __init__(self, imagePath: str):
        self.imagePath = imagePath

    def render(self) -> str:
        # do some modifications if needed
        return f"<img src='{self.imagePath}' />"

    def getElements(self) -> str:
        return self.imagePath
    
class DocumentWithImage(protocols):
    def addImage(self, imageurl: str) -> None: pass

class DocumentWithText(protocols):
    def addText(self, text: str) -> None: pass

class Document(DocumentWithImage, DocumentWithText):
    def getElemets(self) -> list[Element] : pass

class TextDocument(Document):
    def __init__(self):
        self.elements: list[Element] = []

    def addText(self, text: str) -> None:
        text_element = TextElement(text)
        self.elements.append(text_element)

    def addImage(self, imageurl: str) -> None:
        image_element = ImageElement(imageurl)
        self.elements.append(image_element)

    def getElemets(self) -> list[Element]:
        return self.elements

class Persistence(protocols):
    def save(self, content:str, path:str) -> None : pass

class SaveAsTextFile(Persistence):
    def save(self, content: str, path: str) -> None:
        with open(path, 'w') as file:
            file.write(content)

class Renderer(protocols):
    def render(self, elements: list[Element]) -> str : pass

class TextRenderer(Renderer):
    def render(self, elements: list[Element]) -> str:
        rendered_content = ""
        for element in elements:
            rendered_content += element.render() + "\n"
        return rendered_content

class Formatter(protocols):
    def format(self, element: Element) -> Element : pass

class TextFormatter(Formatter): pass

class UpperCaseFormatter(TextFormatter):
    def format(self, element: Element) -> Element:
        if isinstance(element, TextElement):
            element.text = element.text.upper()
        return element

class LowerCaseFormatter(TextFormatter):
    def format(self, element: Element) -> Element:
        if isinstance(element, TextElement):
            element.text = element.text.lower()
        return element

class BoldFormatter(TextFormatter):
    def format(self, element: Element) -> Element:
        if isinstance(element, TextElement):
            element.text = f"**{element.text}**"
        return element

if __name__=="__main__":
    
    document = TextDocument()
    document.addText("Hello, World!")
    document.addImage("path/to/image.png")
    document.addText("This is a sample document.")

    # Apply formatters
    formatters = [UpperCaseFormatter(), BoldFormatter()]
    for i, element in enumerate(document.getElemets()):
        for formatter in formatters:
            element = formatter.format(element)
        document.getElemets()[i] = element

    # Render document
    renderer = TextRenderer()
    rendered_content = renderer.render(document.getElemets())

    print(rendered_content)

    # Save document
    persistence = SaveAsTextFile()
    persistence.save(rendered_content, "output.txt")
    print("Document saved as output.txt")
